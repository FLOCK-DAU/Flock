package com.Flock.domain.Club.Entity;

import com.Flock.domain.Club.Entity.Enum.DayOfWeek;
import com.Flock.domain.Member.Entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    private String title;

    // 최대 인원수 제한
    private Integer maximum;

    // 활동 위치
    private String location;

    // 활동일자
    private LocalDateTime activityDate;

    // 활동 요일
    private String activityDays;

    // 활동 주기 (1주 or 격주 ....)
    private Integer activityFrequency;


    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member manager;


    /**
     *  이 모임의 활동 요일 반환
     */
    public List<DayOfWeek> getActivityDays(){
        List<DayOfWeek> days = Arrays.stream(this.activityDays.split(","))
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toList());
        return days;
    }

    /**
     * 모임의 활동 요일 설정
     */
    public void setActivityDays(List<DayOfWeek> days) {
        String activityDays = days.stream()
                .map(DayOfWeek::name)
                .collect(Collectors.joining(","));

        // 이 부분에서 activityDays를 엔티티의 필드에 할당합니다.
        this.activityDays = activityDays;
    }
}
