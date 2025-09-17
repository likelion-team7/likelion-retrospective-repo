package BookJava.main.adminpage;

import Repository.BookDAO;
import Repository.BookDAOImpl;
import Sql.DTO.BookDTO;

import java.util.Scanner;

public class Adminpage {
    BookDAO bookDAO =  new BookDAOImpl();
    Scanner scanner = new Scanner(System.in);

    public  void Adminpage(){
        while (true) {
            System.out.println("========================\n");
            System.out.println("관리자 페이지로 이동합니다\n");
            System.out.println("========================\n");
            System.out.println("원하시는 작업을 선택해 주세요\n");
            System.out.println("1.도서 추가  |  2. 도서 삭제  | 3. 도서 수정 | 4. 뒤로가기\n");
            int admininput = 4;
            try { //메뉴선택 예외처리
                admininput = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                System.out.println("메뉴에있는 숫자를 선택해주세요");
            }
            switch (admininput) {

                case 1:
                    System.out.println("추가하실 책을 입력해 주세요 : ");
                    System.out.println("책이름 : ");
                    String addtitle = scanner.nextLine();
                    System.out.println("지은이 : ");
                    String addauthor = scanner.nextLine();

                    if (addtitle.isEmpty() || addauthor.isEmpty()) { //if문으로 값이 안들어올때만 예외처리
                        System.out.println("값이 입력되지 않았습니다. 다시 시도해주세요 \n");
                        break;
                    } else {
                        bookDAO.addBook(new BookDTO(addtitle, addauthor));
                        System.out.println("도서가 추가되었습니다.");
                    }
                    break;

                case 2:
                    System.out.println("삭제하실 책의 ID를 입력해 주세요 : ");
                    String subid = scanner.nextLine(); //스캔값을 String으로 받고 공백이 아니라면 숫자로 변환
                    if (subid.isEmpty()) {
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요");
                        break;
                    }
                    try { //의도하지 않은 값에대한 처리
                        int delid = Integer.parseInt(subid); //int값으로 변환 변환실패시 예외처리
                        bookDAO.deleteBook(new BookDTO(delid));

                        System.out.println("도서가 삭제되었습니다.");
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " 잘못된입력입니다.");
                        break;
                    }


                case 3:
                    try {
                        System.out.println("수정할 도서를 입력해 주세요 :");
                        System.out.println("새 책이름 : ");
                        String updatetitle = scanner.nextLine();
                        System.out.println("지은이 : ");
                        String updateauthor = scanner.nextLine();
                        System.out.println("기존 책의 ID : ");
                        String subupdateid = scanner.nextLine();

                        if (subupdateid.isEmpty() || updatetitle.isEmpty() || updateauthor.isEmpty()) {
                            System.out.println("잘못된 입력입니다. 다시 시도해주세요");
                            break;
                        }
                        int updateid = Integer.parseInt(subupdateid);
                        bookDAO.updateBook(new BookDTO(updateid, updatetitle, updateauthor));
                        System.out.println("도서가 수정되었습니다.");

                    } catch (Exception e) { //ID에대한 예외처리
                        System.out.println(e.getMessage() + " 잘못된입력입니다.");
                        break;
                    }

                    break;

                case 4:
                    System.out.println("\n이전 페이지로 이동합니다.");
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                    continue;
            }
            if (admininput == 4) {
                break;
            }
        }
    }
}

