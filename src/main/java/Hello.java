import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Hello extends HttpServlet {

    public int giveRandom(){
        Random r = new Random();
        return r.nextInt(256);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("image/png");

        BufferedImage bufferedImage = new BufferedImage(640, 120, 1);
        Graphics g = bufferedImage.createGraphics();
        Color randomColor = new Color(giveRandom(), giveRandom(), giveRandom());
        g.setColor(randomColor);
        Font font = new Font("Arial",Font.BOLD,72);
        g.setFont(font);
        g.drawString("Hello World!",100,100);
        ImageIO.write(bufferedImage,"PNG",resp.getOutputStream());
    }
}
