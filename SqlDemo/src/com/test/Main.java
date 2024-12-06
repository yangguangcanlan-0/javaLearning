package com.test;

import com.test.entity.Student;
import com.test.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try(SqlSession session = MybatisUtil.getSession(true)){
            TestMapper mapper = session.getMapper(TestMapper.class);
            mapper.selectStudent().forEach(System.out::println);

        }
    }
}