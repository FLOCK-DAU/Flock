package com.Flock.domain.Club.Service;

import com.Flock.domain.Club.DTO.ClubRequestDto;
import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.Enum.DayOfWeek;
import com.Flock.domain.Club.Repository.ClubRepository;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ClubRepository clubRepository;


    public CommonResponse createClub(ClubRequestDto clubRequestDto, Long memberId){

        Optional<Member> member = memberRepository.findById(memberId);

        List<DayOfWeek> dayOfWeeks = DayOfWeek.from(clubRequestDto.getActivityDays());

        Club club = Club.builder()
                .title(clubRequestDto.getTitle())
                .introduce(clubRequestDto.getIntroduce())
                .maximum(clubRequestDto.getMaximum())
                .activityDate(calculateNextActivityDate(LocalDateTime.now(), dayOfWeeks, clubRequestDto.getActivityFrequency()))
                .activityDays(DayOfWeek.activityDaysToString(dayOfWeeks))
                .activityFrequency(clubRequestDto.getActivityFrequency())
                .secret(clubRequestDto.getSecret())
                .isRecruitment(true)
                .manager(member.get()).build();


        clubRepository.save(club);

        return new CommonResponse(member.get().getMemberName() + "이 만든 클럽은 성공적으로 저장됨");

    }






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
            case MONDAY: return java.time.DayOfWeek.MONDAY;
            case TUESDAY: return java.time.DayOfWeek.TUESDAY;
            case WEDNESDAY: return java.time.DayOfWeek.WEDNESDAY;
            case THURSDAY: return java.time.DayOfWeek.THURSDAY;
            case FRIDAY: return java.time.DayOfWeek.FRIDAY;
            case SATURDAY: return java.time.DayOfWeek.SATURDAY;
            case SUNDAY: return java.time.DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Unknown day of week: " + dayOfWeek);
        }
    }
}
