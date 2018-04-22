package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.w3c.dom.css.Counter;

public class GamePlay extends JPanel implements KeyListener , ActionListener, Runnable{
    public ImageIcon titleImage;
    private ImageIcon head , middle , tail;
    private int fx[] = new int[1000];
    private int fy[] = new int[1000];
    private int upDown = 10;
    private int snakeLength = 4;
    private boolean left  , right = true  , up , down;
    private Timer timer;
    private String prev = null;
    private int delay = 500;
    private int counter = 0;
    private int remainUp;
    private int remainDown;


    private int []enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800};
     private int []enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575};

    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(32);
     private int ypos = random.nextInt(21);


    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }


     public void paint(Graphics g){
        if(counter == 0){
            fx[0] = 160;
            fx[1] = fx[0]-20;
            fx[2] = fx[1]-20;
            fx[3] = fx[2]-20;
            fy[0] = 150;
            fy[1] = 150+upDown;
            fy[2] = fy[0];
            fy[3] = fy[1];
        }
        g.setColor(Color.black);
        g.drawRect(0, 75, 820, 600);
        g.fillRect(0, 75, 820, 600);
        titleImage = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\snaketitle.jpg");
        titleImage.paintIcon(this, g, 0, 10);
        middle = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\snakeimage.png");
        // printing head
        if(up == true){
            head = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\upmouth.png");
            head.paintIcon(this, g, fx[0], fy[0]);
        }
        if(left == true){
            head = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\leftmouth.png");
            head.paintIcon(this, g, fx[0], fy[0]);
        }
        if(right == true){
            head = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\rightmouth.png");
            head.paintIcon(this, g, fx[0], fy[0]);
        }
        if(down == true){
            head = new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\downmouth.png");
            head.paintIcon(this, g, fx[0], fy[0]);
        }
        for(int i  = 1 ; i < snakeLength ; i++){
            middle.paintIcon(this, g, fx[i], fy[i]);

        enemyimage =new ImageIcon("C:\\Users\\Mishkat\\Desktop\\snake\\src\\snake\\enemy.png");
         if(enemyxpos[xpos]==fx[0] && enemyypos[ypos]==fy[0])
         {
            snakeLength++;
            xpos=random.nextInt(32);
            ypos=random.nextInt(21);

         }
       enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
       g.dispose();


    }

//    @Override
//     void keyTyped(KeyEvent e) {
//
//    }
//
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            counter++;

            if(left == true) prev = "left";
            if(right == true) prev = "right";
            if(up == true) prev = "up";
            if(down == true) prev = "down";

            if(left == true || right == true){
                up = false;
                down = true;
                left = false;
                right = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            counter++;

            if(left == true) prev = "left";
            if(right == true) prev = "right";
            if(up == true) prev = "up";
            if(down == true) prev = "down";

            if(down == true || up == true){
                up = false;
                down = false;
                left = true;
                right = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            counter++;

            if(left == true) prev = "left";
            if(right == true) prev = "right";
            if(up == true) prev = "up";
            if(down == true) prev = "down";

            if(left == true || right == true){
                up = true;
                down = false;
                left = false;
                right = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            counter++;

            if(left == true) prev = "left";
            if(right == true) prev = "right";
            if(up == true) prev = "up";
            if(down == true) prev = "down";

            if(down == true || up == true){
                up = false;
                down = false;
                left = false;
                right = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
               timer.start();
               if(up == true){

                   for(int i = snakeLength ; i > 0 ; i--){
                       fx[i] = (fx[i-1]);
                       fy[i] = (fy[i-1]);
                   }
                   int save = (fx[2]-fx[1]);
                   save /= Math.abs(save);
                   fx[0] = (fx[1]+ save*upDown+800)%800;
                 //  if(remainDown == 1) fx[0]+=10;
                   int temp = fy[1]-20;
                   if(temp < 80) temp = temp-80;
                   fy[0] = (temp+600)%600;
                   repaint();
               }
               if(down == true){
                   for(int i = snakeLength ; i > 0 ; i--){
                       fx[i] = (fx[i-1]);
                       fy[i] = fy[i-1];
                   }
                   int save = (fx[2]-fx[1]);
                   save /= Math.abs(save);
                   fx[0] = (fx[1]+ save*upDown + 800)%800;
                   int temp = fy[1]+20;
                   if(temp <= 80 ||  temp > 600) temp += 80;
                   fy[0] = (temp+600)%600;
                   repaint();
               }
               if(left == true){
                   for(int i = snakeLength ; i> 0 ; i--){
                      // System.out.println(fx[i] + ' ' + fy[i]);
                       fx[i] = (fx[i-1])%800;
                       fy[i] = (fy[i-1])%800;
                   }
                   int save = fy[2]-fy[1];
                   save /= Math.abs(save);
                   fx[0] = (fx[1]-20+800)%800;
                   fy[0] = (fy[1] + save*upDown+800)%800;
                //   repaint();
                   repaint();
               }
               if(right == true){
                   for(int i = snakeLength ; i> 0 ; i--){
                      // System.out.println(fx[i] + ' ' + fy[i]);
                       fx[i] = (fx[i-1])%800;
                       fy[i] = (fy[i-1])%800;
                   }
                   int save = fy[2]-fy[1];
                   save /= Math.abs(save);
                   fx[0] = (fx[1]+20+800)%800;
                   fy[0] = (fy[1] + save*upDown+800)%800;
                   repaint();
               }
    }

//    @Override
//    public void run() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
