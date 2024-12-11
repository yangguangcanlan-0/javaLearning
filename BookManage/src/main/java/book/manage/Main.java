package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.mapper.BookMapper;
import book.manage.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("==========================");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.录入借阅信息");
                System.out.println("4.查询借阅信息");
                System.out.print("输入您想要执行的操作(输入其他任意数字退出)：");
                int input;
                try {
                    input = scanner.nextInt();
                } catch (Exception e) {
                    return;
                }
                scanner.nextLine();
                switch (input) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        queryBorrow(scanner);
                        break;
                    default:
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void queryBorrow(Scanner scanner){
        SqlUtil.doSqlWork(mapper -> {
            System.out.println(mapper.getBorrowList());
        });
    }
    private static void addBorrow(Scanner scanner) {
        System.out.println("请输入书籍号：");
        String a = scanner.nextLine();
        int bid = Integer.parseInt(a);
        System.out.println("请输入学号：");
        String b = scanner.nextLine();
        int sid = Integer.parseInt(b);
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addBorrow(sid, bid);
            if(i > 0){
                System.out.println("借阅信息录入成功");
                log.info("新添加了一条借阅信息");
            }else{
                System.out.println("借阅信息录入失败，请重试");
            }
        });
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入学生性别（男/女）：");
        String sex = scanner.nextLine();
        System.out.println("请输入学生年级：");
        String grade = scanner.nextLine();
        int g = Integer.parseInt(grade);
        Student student = new Student(name, sex, g);
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addStudent(student);
            if (i > 0) {
                System.out.println("学生信息录入成功");
                log.info("新添加了一条学生信息" + student);
            } else System.out.println("学生信息录入失败，请重试");
        });
    }

    private static void addBook(Scanner scanner) {
        System.out.println("请输入书籍标题：");
        String title = scanner.nextLine();
        System.out.println("请输入书籍描述：");
        String description = scanner.nextLine();
        System.out.println("请输入书籍价格：");
        String price = scanner.nextLine();
        double p = Double.parseDouble(price);
        Book book = new Book(title, description, p);
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addBook(book);
            if (i > 0) {
                System.out.println("书籍信息录入成功");
                log.info("新添加了一条书籍信息" + book);
            } else System.out.println("书籍信息录入失败，请重试");
        });
    }

}
