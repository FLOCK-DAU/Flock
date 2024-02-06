package com.Flock.domain.Club.Service;

import com.Flock.domain.Category.Entity.Category;
import com.Flock.domain.Category.Repository.CategoryRepository;
import com.Flock.domain.Club.DTO.ClubListDto;
import com.Flock.domain.Club.DTO.Request.ClubRequestDto;
import com.Flock.domain.Club.DTO.Response.ClubResponseDto;
import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Club.Entity.Enum.DayOfWeek;
import com.Flock.domain.Club.Repository.ClubRepository;
import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.ClubMember.Service.ClubMemberService;
import com.Flock.domain.Likes.Service.LikesService;
import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.SingleResponse;
import com.Flock.domain.Tag.DTO.TagDto;
import com.Flock.domain.Tag.Service.TagService;
import com.Flock.global.Exception.CustomErrorCode;
import com.Flock.global.Exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClubService {


    private final MemberService memberService;

    private final ClubRepository clubRepository;

    private final ClubTagService clubTagService;

    private final LikesService likesService;

    private final ClubMemberService clubMemberService;

    private final CategoryRepository categoryRepository;

    private final TagService tagService;


    /**
     * 쿨럽 생성
     */
    public CommonResponse createClub(ClubRequestDto clubRequestDto, Long memberId) {

        Optional<Member> member = memberService.findById(memberId);

        Category category = categoryRepository.findById(clubRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리 정보가 없습니다."));


        List<DayOfWeek> dayOfWeeks = DayOfWeek.from(clubRequestDto.getActivityDays());

//        Gender gender = Gender.fromDescription(clubRequestDto.getGender());

//        if (!gender.equals(Gender.MIXED)) gender = member.get().getGender();

        Club club = Club.builder()
                .title(clubRequestDto.getTitle())
                .introduce(clubRequestDto.getIntroduce())
                .maximum(clubRequestDto.getMaximum())
                .activityDate(calculateNextActivityDate(LocalDateTime.now(), dayOfWeeks, clubRequestDto.getActivityFrequency()))
                .activityDays(DayOfWeek.activityDaysToString(dayOfWeeks))
                .activityFrequency(clubRequestDto.getActivityFrequency())
                .secret(clubRequestDto.getSecret())
                .isRecruitment(true)
                .gender(clubRequestDto.getGender())
                .category(category)
                .manager(member.get()).build();


        clubRepository.save(club);

        clubTagService.saveClubTag(club, clubRequestDto.getTags());


        return new CommonResponse(member.get().getMemberName() + "이 만든 클럽은 성공적으로 저장됨");

    }

    /**
     * 클럽 상세정보
     */
    public ClubResponseDto getClub(Long clubId, Long memberId) {

        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.CLUB_NOT_FOUND));

        List<MemberDto> memberDto = memberService.findByClubMemberId(club.getClubMembers());

        List<TagDto> tagDtos = tagService.findByClub(club);

        boolean isManager = false;

        if (member.getId() == club.getManager().getId()) isManager = true;

        ClubResponseDto clubResponseDto = new ClubResponseDto(club,memberDto,tagDtos, isManager);

        return clubResponseDto;

    }


    /**
     * 클럽 리스트 조회 (카테고리 : 전체)
     * 검색 조건이 있을텐데 나중에 추가해주기
     */
    public List<ClubListDto> getClubs(String title, String tag) {

        List<ClubListDto> clubs = new ArrayList<>();

        if (title == null && tag == null) {
            clubs = clubRepository.findAllClubs();
        } else if (title != null && tag == null) {
            clubs = clubRepository.findClubsByTitle(title);
        } else if (title == null && tag != null) {
            List<ClubTag> clubtag = clubTagService.findByTagName(tag);
            clubs = clubRepository.findClubsByTag(clubtag);
        }

        return clubs;

//        List<Club> clubs = clubRepository.findAllWithLikesAndMembers();
//
//        return clubs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * 클럽 리스트 조회 (카테고리 적용)
     */
    public List<ClubListDto> getClubsWithCategoryId(Long categoryId, String title, String tag) {


        List<ClubListDto> clubs = new ArrayList<>();

        if (title == null && tag == null) {
            clubs = clubRepository.findAllClubsWithCategory(categoryId);
        } else if (title != null && tag == null) {
            clubs = clubRepository.findClubsByTitleWithCategory(categoryId, title);
        } else if (title == null && tag != null) {
            List<ClubTag> clubtag = clubTagService.findByTagName(tag);
            clubs = clubRepository.findClubsByTagWithCategory(categoryId, clubtag);
        }

        return clubs;
    }


//    private ClubListDto convertToDto1(Club club){
//        List<String> tagNames = clubTagService.findByClub(club)
//                .stream()
//                .map(clubTag -> clubTag.getTag().getTagName())
//                .collect(Collectors.toList());
//
//        Integer clubLikesCount = likesService.getLikesCount(club.getId());
//
//        Integer clubMemberCount = clubMemberService.countClubMember(club);
//
//        return new ClubListDto(club,tagNames,clubLikesCount,clubMemberCount);
//    }


    // fetch join을 사용한 방법
//    private ClubListDto convertToDto2(Club club){
//        List<String> tagNames = clubTagService.findByClub(club)
//                .stream()
//                .map(clubTag -> clubTag.getTag().getTagName())
//                .collect(Collectors.toList());
//
//        Long clubLikesCount = Long.valueOf(club.getLikes().size());
//
//        Long clubMemberCount = clubMemberService.countClubMember(club);
//
//        return new ClubListDto(club,clubLikesCount,clubMemberCount,tagNames);
//    }

    /**
     * 활동 주기와 활동 요일을 통해 활동일자 계산
     */

    public LocalDateTime calculateNextActivityDate(LocalDateTime startDate, List<DayOfWeek> activityDays, int activityFrequency) {
        LocalDateTime nextActivityDate = null;

        for (DayOfWeek day : activityDays) {
            java.time.DayOfWeek javaDayOfWeek = convertToJavaDayOfWeek(day);
            LocalDateTime tempDate = startDate.with(TemporalAdjusters.nextOrSame(javaDayOfWeek));
            if (nextActivityDate == null || tempDate.isBefore(nextActivityDate)) {
                nextActivityDate = tempDate;
            }
        }

        if (activityFrequency > 1) {
            nextActivityDate = nextActivityDate.plusWeeks(activityFrequency - 1);
        }

        return nextActivityDate;
    }

    private java.time.DayOfWeek convertToJavaDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return java.time.DayOfWeek.MONDAY;
            case TUESDAY:
                return java.time.DayOfWeek.TUESDAY;
            case WEDNESDAY:
                return java.time.DayOfWeek.WEDNESDAY;
            case THURSDAY:
                return java.time.DayOfWeek.THURSDAY;
            case FRIDAY:
                return java.time.DayOfWeek.FRIDAY;
            case SATURDAY:
                return java.time.DayOfWeek.SATURDAY;
            case SUNDAY:
                return java.time.DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Unknown day of week: " + dayOfWeek);
        }
    }


}
