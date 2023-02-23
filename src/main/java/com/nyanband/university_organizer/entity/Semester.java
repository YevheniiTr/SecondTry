package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Semester extends BaseEntity {
    @Column(name = "num")
    Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    Course course;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    List<Discipline> disciplines;
//    @OneToOne(mappedBy = "semester", fetch = FetchType.LAZY)
//    Schedule schedule;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
//    Schedule schedule;

    public Semester(Integer number, Course course) {
        this.number = number;
        this.course = course;
    }

    public Semester(Long id) {
        super(id);
    }
}
