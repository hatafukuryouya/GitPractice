package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KadaiDAO;

/**
 * Servlet implementation class KadaiDelExeServlet
 */
@WebServlet("/KadaiDelExeServlet")
public class KadaiDelExeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiDelExeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				
				// フォーム入力内容の取得
				String mail = (String)session.getAttribute("input_data");
				
				
				// SQL実行
				int result = KadaiDAO.deleteKadai16(mail);
				String view="";
				if(result == 1) {
					// 登録に成功したので、sessionのデータを削除
					session.removeAttribute("input_data");
					 view = "WEB-INF/view/deletesuccess.jsp";
					
				} else {
					// 登録に失敗してもsessionのデータを削除
					session.removeAttribute("input_data");
					 view = "WEB-INF/view/deletefail.jsp";
					
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
