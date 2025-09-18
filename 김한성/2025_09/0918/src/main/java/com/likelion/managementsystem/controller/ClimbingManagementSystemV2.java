package com.likelion.managementsystem.controller;


import com.likelion.managementsystem.domain.MemberShipType;
import com.likelion.managementsystem.domain.Visit;
import com.likelion.managementsystem.repository.MemberRepository;
import com.likelion.managementsystem.repository.MembershipRepository;
import com.likelion.managementsystem.repository.VisitRepository;
import com.likelion.managementsystem.repository.database.DBMemberRepository;
import com.likelion.managementsystem.repository.database.DBMembershipRepository;
import com.likelion.managementsystem.repository.database.DBVisitRepository;
import com.likelion.managementsystem.service.ManagementService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ClimbingManagementSystemV2 {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static MemberRepository memberRepository = new DBMemberRepository();
    public static VisitRepository visitRepository = new DBVisitRepository();
    public static MembershipRepository membershipRepository = new DBMembershipRepository();
    public static ManagementService service = new ManagementService(memberRepository, visitRepository, membershipRepository);
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        boolean flag = true;
        while (flag) {
            printMenu();
            int choice = scan.nextInt();z
            switch (choice) {
                case 1 -> handleVisitor();
                case 2 -> handleUseMemberShip();
                case 3 -> handleRegisterMember();
                case 4 -> handleDeleteMember();
                case 5 -> handleCheckVisitors();
                case 6 -> flag = false;
                default -> System.out.println("[오류] 잘못된 선택입니다.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("=============멋사 클라이밍==============");
        System.out.println("[메뉴 선택]");
        System.out.println("1. 일일이용 \t2. 회원권 사용 \t3. 회원가입\t");
        System.out.println("4. 회원 탈퇴\t5. 일일이용 확인\t6. 나가기");
    }

    private static void handleVisitor() {
        System.out.print("이름 : ");
        String name = scan.next();
        System.out.print("전화 번호 : ");
        String phoneNumber = scan.next();
        service.createVisitor(name, phoneNumber);
    }

    private static void handleUseMemberShip() {
        System.out.print("전화번호 입력 : ");
        String number = scan.next();
        service.useMemberShip(number);
    }

    private static void handleRegisterMember() {
        System.out.println("[회원 가입을 위한 정보 입력]");
        System.out.print("이름 : ");
        String newName = scan.next();
        System.out.print("전화번호 : ");
        String newNum = scan.next();
        if (service.duplicatedPhoneNumberCheck(newNum)) {
            System.out.println("[오류] 이미 존재하는 회원입니다.");
            return;
        }
        System.out.println("[회원권 선택]");
        System.out.println("1. 1개월권 2. 3개월권 3. 10회권 4. 30회권");
        String memberShip = scan.next();

        MemberShipType type = switch (memberShip) {
            case "1" -> MemberShipType.ONE_MONTH;
            case "2" -> MemberShipType.THREE_MONTH;
            case "3" -> MemberShipType.TEN_SESSION;
            case "4" -> MemberShipType.THIRTY_SESSION;
            default -> null;
        };

        if (type == null) {
            System.out.println("[오류] 잘못된 선택입니다.");
            return;
        }
        service.createMemberShip(newName, newNum, type);
    }

    private static void handleDeleteMember() {
        System.out.println("[회원 탈퇴]");
        System.out.println("[전화번호 입력]");
        String delete = scan.next();
        service.deleteMember(delete);
    }

    private static void handleCheckVisitors() {
        List<Visit> allVisits = service.getAllVisits();
        for (Visit visitor : allVisits) {
            System.out.println("이름 : " + visitor.getName() + ", 전화번호 : " + visitor.getPhone() + ", 방문날짜 : " + visitor.getVisitedAt().format(FORMATTER));
        }
    }
}
