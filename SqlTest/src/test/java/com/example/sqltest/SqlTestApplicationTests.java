package com.example.sqltest;

import com.example.sqltest.mbg.entity.Course;
import com.example.sqltest.mbg.entity.Sc;
import com.example.sqltest.mbg.entity.Student;
import com.example.sqltest.mbg.entity.Teacher;
import com.example.sqltest.mbg.mapper.CourseMapper;
import com.example.sqltest.mbg.mapper.ScMapper;
import com.example.sqltest.mbg.mapper.StudentMapper;
import com.example.sqltest.mbg.mapper.TeacherMapper;
import com.example.sqltest.model.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.diff.myers.DiffNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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

            int i = 0, j = 0;
            for (String s : cIdListBySid) {
                if (cIdList.contains(s))
                    ++i;
            }
            for (String s : cIdList) {
                if (cIdListBySid.contains(s))
                    ++j;
            }
            if (i == j && i == cIdList.size()) {
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
     *
     * 查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
     */
    @Test
    void T11() {
        //        HashMap map = new HashMap<String, HashMap<String, String>>();
//        for (Student student : studentList) {
//            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
//            if (mapBySid.get("01") != null && mapBySid.get("02") != null && mapBySid.get("03") != null)
//                continue;
//
//            map.put(student.getSid(), mapBySid);
//        }

        Set set = new HashSet<>();
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
//        Map map = new HashMap<>();
//        for (Student student : studentList) {
//            Map<String, Integer> scMapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
//            map.put(student.getSid(), scMapBySid);
//        }
//        for (Student student : studentList) {
//            Map<String, Integer> mapBySid = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
//            int count = (int) scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).map(Sc::getCid).count();
//            int sum = scList.stream().filter(sc -> Objects.equals(sc.getSid(), student.getSid())).mapToInt(Sc::getScore).sum();
//            if (sum == 0)
//                continue;
//            int average = sum / ((int) count);
//            if (average >= 60) {
//                map.put(student.getSid(), mapBySid);
//            }
//        }

        List<String> sIdByPassList = studentList.stream().map(
                student -> StudentCourseScDTO.builder()
                        .sid(student.getSid())
                        .courseScDTOList(
                                scList.stream()
                                        .filter(sc -> Objects.equals(sc.getSid(), student.getSid()))
                                        .filter(sc -> sc.getScore() >= 60)
                                        .map(sc ->
                                                CourseScDTO.builder()
                                                        .cid(sc.getCid())
                                                        .score(sc.getScore())
                                                        .build()
                                        ).collect(Collectors.toList())
                        ).build()).filter(studentCourseScDTO -> studentCourseScDTO.getCourseScDTOList().size() > 1).map(StudentCourseScDTO::getSid).collect(Collectors.toList());

        List<StudentAveScDTO> studentAveScDTOList = studentList.stream().filter(student -> !sIdByPassList.contains(student.getSid())).map(
                student -> StudentAveScDTO.builder()
                        .sid(student.getSid())
                        .name(student.getSname())
                        .aveSc(null)
                        .build()
        ).peek(
                studentAveScDTO -> {
                    int count = (int) scList.stream().filter(sc -> Objects.equals(sc.getSid(), studentAveScDTO.getSid())).map(Sc::getCid).count();
                    int sum = scList.stream().filter(sc -> Objects.equals(sc.getSid(), studentAveScDTO.getSid())).mapToInt(Sc::getScore).sum();
                    if (count > 0) {
                        studentAveScDTO.setAveSc(sum / count);
                    }
                }
        ).collect(Collectors.toList());

        log.info(Jackson.toString(studentAveScDTOList));

    }

    /**
     * 检索" 01 "课程分数小于 60，按分数降序排列的学生信息
     */
    @Test
    void T12() {
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
        List<Sc> scList1 = scList.stream().filter(sc -> sc.getCid().equals("01")).filter(sc -> sc.getScore() < 60).collect(Collectors.toList());
        List<Student> studentList1 = studentList.stream().filter(student -> scList1.stream().map(Sc::getSid).collect(Collectors.toList()).contains(student.getSid())).collect(Collectors.toList());
        scList1.sort((a, b) -> (b.getScore().compareTo(a.getScore())));
        log.info(Jackson.toString(scList1));
    }

    /**
     * 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
     */
    @Test
    void T13() {
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
        List<StudentScDTO> studentScDTOList = studentList.stream().map(student -> StudentScDTO.builder().sid(student.getSid()).aveSc(0).courseScDTOList(null).build())
                .peek(
                        studentScDTO -> {
                            int sum = scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).mapToInt(Sc::getScore).sum();
                            int count = (int) scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).map(Sc::getCid).count();
                            int counts = 3;
                            if (count > 0) {
                                studentScDTO.setAveSc(sum / counts);
                            }
                        }
                ).peek(
                        studentScDTO ->
                                studentScDTO.setCourseScDTOList(scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).map(
                                        sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build()
                                ).collect(Collectors.toList()))


                ).collect(Collectors.toList());
        studentScDTOList.sort((a, b) -> (b.getAveSc().compareTo(a.getAveSc())));
//        studentScDTOList.stream().filter()

        log.info(Jackson.toString(studentScDTOList));
    }

    /**
     * 查询各科成绩最高分、最低分和平均分：
     * 以如下形式显示：课程 ID，课程 name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
     * <p>
     * 及格为>=60，中等为：70-80，优良为：80-90，优秀为：>=90
     * 要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列
     */
    @Test
    void T14() {
        List<Sc> scList = scMapper.selectAll();
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList1 = scList.stream().filter(sc -> sc.getCid().equals("02")).collect(Collectors.toList());
        scList1.sort((a, b) -> (b.getScore().compareTo(a.getScore())));
        Sc sc0 = scList1.get(0);
        log.info(sc0.toString());

        int count = (int) scList.stream().filter(sc -> sc.getCid().equals("02")).count();
        int size = studentList.size();
        int sum = scList.stream().filter(sc -> sc.getCid().equals("02")).mapToInt(Sc::getScore).sum();
//        Assert.isTrue(count==0,"null point");
        int aveScore = sum / count;
        log.info(sum + "\n" + count + "\n" + aveScore + "");


    }

    /**
     * 按各科成绩进行排序，并显示排名， Score 重复时保留名次空缺
     */
    @Test
    void T15() {
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
        List<StudentScDetailDTO> studentScDetailDTOList = studentList.stream().map(student -> StudentScDetailDTO.builder().sid(student.getSid()).aveSc(0).courseScMap(null).build())
                .peek(
                        studentScDetailDTO -> {
                            int sum = scList.stream().filter(sc -> sc.getSid().equals(studentScDetailDTO.getSid())).mapToInt(Sc::getScore).sum();
                            int count = (int) scList.stream().filter(sc -> sc.getSid().equals(studentScDetailDTO.getSid())).map(Sc::getCid).count();
                            int counts = 3;
                            if (count > 0) {
                                studentScDetailDTO.setAveSc(sum / counts);
                            }
                        }
                ).peek(
                        studentScDetailDTO -> {
                            Map<String, Integer> stringIntegerMap = scList.stream().filter(sc -> sc.getSid().equals(studentScDetailDTO.getSid())).collect(Collectors.toMap(Sc::getCid, Sc::getScore));
                            if (stringIntegerMap.size() == 3) {
                                studentScDetailDTO.setCourseScMap(stringIntegerMap);
                            } else {
                                if (!stringIntegerMap.containsKey("01")) {
                                    stringIntegerMap.put("01", 0);
                                }
                                if (!stringIntegerMap.containsKey("02")) {
                                    stringIntegerMap.put("02", 0);
                                }
                                if (!stringIntegerMap.containsKey("03")) {
                                    stringIntegerMap.put("03", 0);
                                }
                                studentScDetailDTO.setCourseScMap(stringIntegerMap);
                            }
                        }
                ).collect(Collectors.toList());

        studentScDetailDTOList.sort((a, b) -> b.getCourseScMap().get("03").toString().compareTo(a.getCourseScMap().get("03").toString()));
        log.info(Jackson.toString(studentScDetailDTOList));
    }

    /**
     * 查询学生的总成绩，并进行排名，总分重复时保留名次空缺
     */
    @Test
    void T16() {
        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();
        List<StudentScDTO> studentScDTOList = studentList.stream().map(student -> StudentScDTO.builder().sid(student.getSid()).aveSc(0).courseScDTOList(null).build())
                .peek(
                        studentScDTO -> {
                            int sum = scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).mapToInt(Sc::getScore).sum();
                            int count = (int) scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).map(Sc::getCid).count();
                            int counts = 3;
                            if (count > 0) {
                                studentScDTO.setAveSc(sum / counts);
                            }
                        }
                ).peek(
                        studentScDTO ->
                                studentScDTO.setCourseScDTOList(scList.stream().filter(sc -> sc.getSid().equals(studentScDTO.getSid())).map(
                                        sc -> CourseScDTO.builder().cid(sc.getCid()).score(sc.getScore()).build()
                                ).collect(Collectors.toList()))


                ).collect(Collectors.toList());
    }

    /**
     * 统计各科成绩各分数段人数：课程编号，课程名称，[100-85]，[85-70]，[70-60]，[60-0] 及所占百分比
     */
    @Test
    void T17() {
        List<Sc> scList = scMapper.selectAll();
        scList.stream().filter(sc -> sc.getCid().equals("03")).filter(sc -> sc.getScore() > 85).collect(Collectors.toList());
        long count = scList.stream().filter(sc -> sc.getSid().equals("03")).count();

    }

    /**
     * 查询各科成绩前三名的记录
     */
    @Test
    void T18() {
        List<Sc> scList = scMapper.selectAll();
        List<Sc> scList1 = scList.stream().filter(sc -> sc.getCid().equals("01")).collect(Collectors.toList());
        scList1.sort((a, b) -> (b.getScore().compareTo(a.getScore())));
    }

    /**
     * 查询每门课程被选修的学生数
     */
    @Test
    void T19() {
        List<Sc> scList = scMapper.selectAll();
        long count = scList.stream().filter(sc -> sc.getCid().equals("01")).map(Sc::getSid).count();
    }

    /**
     * 查询出只选修两门课程的学生学号和姓名
     */
    @Test
    void T20() {

        List<Student> studentList = studentMapper.selectAll();
        List<Sc> scList = scMapper.selectAll();

        List<Student> stList = new ArrayList<>();
        for (Student student : studentList) {
            int count = (int) scList.stream().filter(sc -> sc.getSid().equals(student.getSid())).map(Sc::getCid).count();
            if (count == 2) {
                stList.add(student);
            }

        }
    }

    /**
     * 查询男生、女生人数
     */
    @Test
    void T21() {
        List<Student> studentList = studentMapper.selectAll();
        List<Student> studentList1 = studentList.stream().filter(student -> student.getSsex().equals("男")).collect(Collectors.toList());

    }

    /**
     * 查询名字中含有「风」字的学生信息
     */
    @Test
    void T22() {
        List<Student> studentList = studentMapper.selectAll();
        List<Student> studentList1 = studentList.stream().filter(student -> student.getSname().contains("风")).collect(Collectors.toList());

    }

    /**
     * 查询同名同性学生名单，并统计同名人数
     */
    @Test
    void T23() {
        List<Student> students = studentMapper.selectAll();
        int count = 0;
        Set set = new HashSet<>();
        List<Student> studentList = null;
        for (Student student : students) {
            String s = student.getSname() + student.getSsex();
            set.add(s);
            ++count;
            if (count != set.size()) {
                studentList.add(student);
                --count;
            }
        }
    }

    /**
     * 查询 1990 年出生的学生名单查询 1990 年出生的学生名单
     */
    @Test
    void T24() {


    }

    /**
     * TODO
     *  查询每门课程的平均成绩，结果按平均成绩降序排列，平均成绩相同时，按课程编号升序排列
     */
    @Test
    void T25() {
    }

    /**
     * 查询平均成绩大于等于 85 的所有学生的学号、姓名和平均成绩
     */
    @Test
    void T26() {
    }


    @Test
    void T27() {
    }

    @Test
    void T28() {
    }

    @Test
    void T29() {
    }

    @Test
    void T30() {
    }

    @Test
    void T31() {
    }

    @Test
    void T32() {
    }

    @Test
    void T33() {
    }

    @Test
    void T34() {
    }

    /**
     * 查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩
     */
    @Test
    void T35() {
    }

    @Test
    void T36() {
    }

    @Test
    void T37() {
    }
}
