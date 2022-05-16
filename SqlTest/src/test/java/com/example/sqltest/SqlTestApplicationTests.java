package com.example.sqltest;

import com.example.sqltest.mbg.entity.Course;
import com.example.sqltest.mbg.entity.Sc;
import com.example.sqltest.mbg.entity.Student;
import com.example.sqltest.mbg.entity.Teacher;
import com.example.sqltest.mbg.mapper.CourseMapper;
import com.example.sqltest.mbg.mapper.ScMapper;
import com.example.sqltest.mbg.mapper.StudentMapper;
import com.example.sqltest.mbg.mapper.TeacherMapper;
import com.example.sqltest.model.dto.CourseScDTO;
import com.example.sqltest.model.dto.StudentCourseScDTO;
import com.example.sqltest.model.dto.StudentT4DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
class SqlTestApplicationTests {

    @Resource
    ScMapper scMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    CourseMapper courseMapper;

    @Test
    void contextLoads() {
        List<Sc> scList = scMapper.selectAll();
//        log.info(Jackson.toString(scMapper.selectAll()));
        List<Student> studentList = studentMapper.selectAll();
//        log.info(Jackson.toString(studentList));

        List<Teacher> teacherList = teacherMapper.selectAll();

        String s = "zhang";
        boolean b = s.startsWith("zh");
        HashMap map = new HashMap<String, HashMap<String, String>>();
        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (mapBySid.get("01") == null || mapBySid.get("02") == null)
                continue;
            if (mapBySid.get("01").compareTo(mapBySid.get("02")) > 0) {
                map.put(student.getSid(), mapBySid);
            }
        }
        Set set = map.keySet();
        List<Student> studentList1 = studentList.stream().filter(student -> map.containsKey(student.getSid())).collect(Collectors.toList());
        List<Sc> scList1 = scList.stream().filter(sc -> map.containsKey(sc.getSid())).collect(Collectors.toList());

        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> map.containsKey(student.getSid())).map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList1.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .map(sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build())
                                        .collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());

        log.info(map.toString());
        log.info(Jackson.toString(studentList1));
        log.info(Jackson.toString(scList1));
        log.info(Jackson.toString(studentCourseScDTOList));
    }

    @Test
    void t11() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        HashMap map = new HashMap<String, HashMap<String, String>>();

        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (mapBySid.get("01") != null && mapBySid.get("02") != null)
                map.put(student.getSid(), mapBySid);
        }

        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> map.containsKey(student.getSid())).map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList.stream().filter(sc -> map.containsKey(sc.getSid()))
                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .map(sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build())
                                        .collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());

        log.info(Jackson.toString(map));
        log.info(Jackson.toString(studentCourseScDTOList));
    }

    @Test
    void t12() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        Map map = new HashMap<String, HashMap<String, String>>();

        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (mapBySid.get("01") != null)
                map.put(student.getSid(), mapBySid);
        }
        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> map.containsKey(student.getSid())).map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList.stream().filter(sc -> map.containsKey(sc.getSid()))
                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .map(
                                                sc -> {
                                                    CourseScDTO courseScDTO = new CourseScDTO();
                                                    if (sc.getCid() != null) {
                                                        courseScDTO.setCid(sc.getCid());
                                                        courseScDTO.setScore(sc.getScore());
                                                    } else {
                                                    }
                                                    return courseScDTO;
                                                }
                                        )
                                        .collect(Collectors.toList()))
                        .build()
        ).peek(studentCourseScDTO -> {
            if (!studentCourseScDTO.getCourseScDTOList().stream().map(CourseScDTO::getCid).collect(Collectors.toList()).contains("02")) {
                List<CourseScDTO> courseScDTOList = studentCourseScDTO.getCourseScDTOList();
                courseScDTOList.add(CourseScDTO.builder().cid("02").build());
                studentCourseScDTO.setCourseScDTOList(courseScDTOList);
            }
            List<CourseScDTO> courseScDTOList = studentCourseScDTO.getCourseScDTOList();
            courseScDTOList.sort((a, b) -> b.getCid().compareTo(a.getCid()));
            studentCourseScDTO.setCourseScDTOList(courseScDTOList);
        }).collect(Collectors.toList());


        log.info(Jackson.toString(studentCourseScDTOList));
        log.info(Jackson.toString(map));

    }

    @Test
    void t13() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        HashMap map = new HashMap<String, HashMap<String, String>>();

        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (mapBySid.get("01") == null && mapBySid.get("02") != null)
                map.put(student.getSid(), mapBySid);
        }

        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> map.containsKey(student.getSid())).map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList.stream().filter(sc -> map.containsKey(sc.getSid()))
                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .map(sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build())
                                        .collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());

        log.info(Jackson.toString(map));
        log.info(Jackson.toString(studentCourseScDTOList));
    }

    /**
     * 2
     * 查询平均成绩大于等于 60 分的同学的学生编号和学生姓名和平均成绩
     */
    @Test
    void t2() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        HashMap map = new HashMap<String, HashMap<String, String>>();

        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            long count = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).map(Sc::getCid).count();
            int sum = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).mapToInt(Sc::getScore).sum();
            if (sum == 0)
                continue;
            int average = sum / ((int) count);
            if (average >= 60) {
                map.put(student.getSid(), mapBySid);
            }
        }
        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> map.containsKey(student.getSid())).map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList.stream()
                                        .filter(sc -> map.containsKey(sc.getSid()))
                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .map(sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build())
                                        .collect(Collectors.toList())
                        )
                        .build()
        ).collect(Collectors.toList());
        log.info(Jackson.toString(studentCourseScDTOList));
        log.info(Jackson.toString(map));
    }

    /**
     * 查询在 SC 表存在成绩的学生信息
     */
    @Test
    void t3() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();


//        List<StudentCourseScDTO> studentCourseScDTOList = studentList.stream().filter(student -> scList.stream().map(Sc::getSid).collect(Collectors.toList()).contains(student.getSid())).map(
//                student -> StudentCourseScDTO.builder()
//                        .sid(student.getSid())
//                        .courseScDTOList(
//                                scList.stream()
//                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
//                                        .map(sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build())
//                                        .collect(Collectors.toList())
//                        ).build()
//        ).collect(Collectors.toList());
        List<Student> studentList1 = studentList.stream().filter(student -> scList.stream().map(Sc::getSid).collect(Collectors.toList()).contains(student.getSid())).collect(Collectors.toList());


        log.info(Jackson.toString(studentList1));
    }

    /**
     * 查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩(没成绩的显示为 null )
     */
    @Test
    void t4() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();

        List<StudentT4DTO> studentT4DTOList = studentList.stream().map(
                        student -> StudentT4DTO.builder()
                                .sid(student.getSid())
                                .name(student.getSname())
                                .courseCount((int) scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).map(Sc::getCid).count())
                                .scSum(scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).mapToInt(Sc::getScore).sum())
                                .build()
                ).peek(studentT4DTO ->
                        {
                            if (studentT4DTO.getScSum() == 0)
                                studentT4DTO.setScSum(null);

                        }
                )
                .collect(Collectors.toList());

        log.info(Jackson.toString(studentT4DTOList));
    }

    @Test
    void t41() {

    }

    @Test
    void t5() {
        List<Teacher> teacherList = teacherMapper.selectAll();
        List<Teacher> teacherList1 = teacherList.stream().filter(teacher -> teacher.getTname().startsWith("李")).collect(Collectors.toList());
        long count = teacherList.stream().filter(teacher -> teacher.getTname().startsWith("李")).count();
        log.info(Jackson.toString(teacherList1) + "\n" + count);

    }

    /**
     * 查询学过「张三」老师授课的同学的信息
     */
    @Test
    void t6() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        List<Teacher> teacherList = teacherMapper.selectAll();
        List<Course> courseList = courseMapper.selectAll();
        //TODO
        List<String> stringList = teacherList.stream().filter(teacher -> Objects.equals(teacher.getTname(), "张三")).map(Teacher::getTid).collect(Collectors.toList());

        List<String> cIdList = courseList.stream().filter(
                course ->
                        teacherList.stream()
                                .filter(teacher -> Objects.equals(teacher.getTname(), "张三"))
                                .map(Teacher::getTid)
                                .collect(Collectors.toList())
                                .contains(course.getTid())
        ).map(Course::getCid).collect(Collectors.toList());


        List<Student> studentList1 = studentList.stream().filter(
                student -> scList.stream().filter(
                        sc -> courseList.stream().filter(
                                        course ->
                                                teacherList.stream().filter(teacher -> Objects.equals(teacher.getTname(), "张三"))
                                                        .map(Teacher::getTid)
                                                        .collect(Collectors.toList())
                                                        .contains(course.getTid())

                                )
                                .map(Course::getCid).collect(Collectors.toList()).contains(sc.getCid())
                ).map(Sc::getSid).collect(Collectors.toList()).contains(student.getSid())
        ).collect(Collectors.toList());

        log.info(Jackson.toString(studentList1));
    }

    /**
     * 查询没有学全所有课程的同学的信息
     */
    @Test
    void t7() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        HashMap map = new HashMap<String, HashMap<String, String>>();

        for (Student student : studentList) {
            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (mapBySid.get("01") != null && mapBySid.get("02") != null && mapBySid.get("03") != null)
                continue;

            map.put(student.getSid(), mapBySid);
        }
        Set set = map.keySet();
        List<Student> studentList1 = studentList.stream().filter(student -> set.contains(student.getSid())).collect(Collectors.toList());
        log.info(Jackson.toString(studentList1));
    }

    /**
     * 查询至少有一门课与学号为" 01 "的同学所学相同的同学的信息
     */
    @Test
    void t8() {

        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();

        List<String> cIdList = scList.stream().filter(sc -> Objects.equals(sc.getSid(), "09")).map(Sc::getCid).collect(Collectors.toList());
        List<String> sidList = scList.stream().filter(sc -> cIdList.contains(sc.getCid())).map(Sc::getSid).distinct().collect(Collectors.toList());
        List<Student> studentList1 = studentList.stream().filter(student -> sidList.contains(student.getSid())).filter(student -> !Objects.equals(student.getSid(), "09")).collect(Collectors.toList());

        log.info(Jackson.toString(studentList1));

    }

    /**
     * 查询和" 01 "号的同学学习的课程 完全相同的其他同学的信息
     */
    @Test
    void t9() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();

        List<String> cIdList = scList.stream().filter(sc -> Objects.equals(sc.getSid(), "01")).map(Sc::getCid).collect(Collectors.toList());

//        HashMap map = new HashMap<String, HashMap<String, String>>();
//        for (Student student : studentList) {
//            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
//            if (mapBySid.get("01") != null && mapBySid.get("02") != null && mapBySid.get("03") != null)
//                continue;
//
//            map.put(student.getSid(), mapBySid);
//        }

        Set set = new HashSet<>();
        for (Student student : studentList) {
            List<String> cIdListBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).map(Sc::getCid).collect(Collectors.toList());
            if (cIdListBySid.size() != cIdList.size())
                continue;
            int i = 0, j = 0;
            for (String s : cIdListBySid) {
                if (cIdList.contains(s))
                    ++i;
            }
            for (String s : cIdList) {
                if (cIdListBySid.contains(s))
                    ++j;
            }
            if (i == j) {
                set.add(student.getSid());
            }
        }
        List<Student> studentList1 = studentList.stream().filter(student -> set.contains(student.getSid())).collect(Collectors.toList());

        log.info(Jackson.toString(studentList1));
    }

    /**
     * 查询没学过"张三"老师讲授的任一门课程的学生姓名
     */
    @Test
    void t10() {

        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        List<Teacher> teacherList = teacherMapper.selectAll();
        List<Course> courseList = courseMapper.selectAll();

        List<String> tIdList = teacherList.stream().filter(teacher -> Objects.equals(teacher.getTname(), "张三")).map(Teacher::getTid).collect(Collectors.toList());
        List<String> cIdList = courseList.stream().filter(course -> tIdList.contains(course.getTid())).map(Course::getCid).collect(Collectors.toList());
        List<String> sidList = scList.stream().filter(sc -> cIdList.contains(sc.getCid())).map(Sc::getSid).collect(Collectors.toList());
        List<Student> studentList0 = studentList.stream().filter(student -> !sidList.contains(student.getSid())).collect(Collectors.toList());


        List<Student> studentList1 = studentList.stream().filter(
                student -> !scList.stream().filter(
                        sc -> courseList.stream().filter(
                                        course ->
                                                teacherList.stream().filter(teacher -> Objects.equals(teacher.getTname(), "张三"))
                                                        .map(Teacher::getTid)
                                                        .collect(Collectors.toList())
                                                        .contains(course.getTid())

                                )
                                .map(Course::getCid)
                                .collect(Collectors.toList())
                                .contains(sc.getCid())
                ).map(Sc::getSid).collect(Collectors.toList()).contains(student.getSid())
        ).collect(Collectors.toList());

        log.info(Jackson.toString(studentList1));
    }

    /**
     * TODO
     * 查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
     */
    @Test
    void T11(){
        //        HashMap map = new HashMap<String, HashMap<String, String>>();
//        for (Student student : studentList) {
//            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
//            if (mapBySid.get("01") != null && mapBySid.get("02") != null && mapBySid.get("03") != null)
//                continue;
//
//            map.put(student.getSid(), mapBySid);
//        }

        Set set =new HashSet<>();
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
        for (Student student : studentList) {
            Map<String, Integer> scMapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
            if (scMapBySid.get("01") != null && scMapBySid.get("02") != null&& scMapBySid.get("02") != null) {
                if ((scMapBySid.get("01")>60&&scMapBySid.get("02")>60)||(scMapBySid.get("01")>60&&scMapBySid.get("03")>60)||(scMapBySid.get("02")>60&&scMapBySid.get("03")>60)) {
                    set.add(student.getSid());
                }
            }

        }
        List<Student> studentList1 = studentList.stream().filter(student -> !set.contains(student.getSid())).collect(Collectors.toList());

        log.info(Jackson.toString(studentList1));

    }

    /**
     *检索" 01 "课程分数小于 60，按分数降序排列的学生信息
     */
    @Test
    void T12(){

    }

    /**
     * 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
     */
    @Test
    void T13(){

    }

    /**
     * 查询各科成绩最高分、最低分和平均分：
     * 以如下形式显示：课程 ID，课程 name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
     *
     * 及格为>=60，中等为：70-80，优良为：80-90，优秀为：>=90
     * 要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列
     *
     */
    @Test
    void T14(){

    }



}
