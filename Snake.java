/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JFrame;

/**
 *
 * @author kamrul
 */
public class Snake {

    
    public static void main(String[] args) {
         JFrame screen = new JFrame();
         screen.setBounds(280, 100, 820, 600);
         screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         screen.setResizable(false);
         screen.setVisible(true);
         GamePlay j = new GamePlay();
         screen.add(j);
    }
    
    
}
