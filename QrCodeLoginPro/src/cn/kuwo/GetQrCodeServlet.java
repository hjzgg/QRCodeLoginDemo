package cn.kuwo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kuwo.util.TwoDimensionCode;

/**
 * 生成二维码图片以及uuid
 * @author zijuntang
 *
 */
public class GetQrCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//生成唯一ID
		int uuid = (int) (Math.random() * 100000);
		String ip = InetAddress.getLocalHost().getHostAddress();
		//二维码内容
		String content = "http://" + ip + ":8080/QrCodeLoginPro/Login.html?uuid" + uuid;
		//生成二维码
		String imgName =  uuid + "_" + (int) (new Date().getTime() / 1000) + ".png";
		String imgPath = request.getServletContext().getRealPath("/")+"img/" + imgName;
		String insertImgPath = request.getServletContext().getRealPath("/")+"img/hjz.jpg";
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(content, imgPath, "png", insertImgPath);
		
		//生成的图片访问地址
		String qrCodeImg = "http://" + ip + ":8080/QrCodeLoginPro/img/" + imgName;
		String jsonStr = "{\"uuid\":" + uuid + ",\"qrCodeImg\":\"" + qrCodeImg + "\"}";
		out.print(jsonStr);
		out.flush();
		out.close();
	}
}
