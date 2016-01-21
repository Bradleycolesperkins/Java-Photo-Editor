import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

import java.lang.Math;

public class Image_Editor extends JFrame 
{

    JButton reset_button, bandw_button, rgb_button , invert_button, slow_gamma_button, fast_gamma_button, convolve_button, convolution_button;
    JFrame frame;
    JLabel image_icon;
    BufferedImage image;   
    File image_file;

    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    String selectedfilestring;

    /*
        This function sets up the GUI and reads an image
    */
    public Image_Editor()
    {
        // image_file = new File("raytrace.jpg");
        // image_file = new File("/Users/Bradley/Documents/workspace/Image Editor/src/raytrace.jpg");

        String currentDirectory = this.getClass().getClassLoader().getResource("").getPath();
        
        selectedfilestring = "raytrace.jpg";
        image_file = new File(selectedfilestring);
        
        
        optionPane = new JOptionPane();
        // String input = (String) optionPane.showInputDialog("Enter A File");
        optionPane.setVisible(true);
        try
        {
           // image_file = new File(input);
           image = ImageIO.read(image_file);
           
            // Sets up the JFrame
            frame = new JFrame("Perkins Photo Editor");
            JMenu menu = new JMenu("Menu");
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menu);
            
            frame.setBounds(200,100,1200,1000);
            frame.setResizable(false);
            Container contentPane = frame.getContentPane();
            
            // Exit Button
            JMenuItem exititem = new JMenuItem("Exit");
            exititem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            menu.add(exititem);
            
           
            JMenuItem openitem = new JMenuItem("Open");
            openitem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                	final JFileChooser filechooser = new JFileChooser();
            		int selectedfile = filechooser.showOpenDialog(null);
        	 		if (selectedfile == JFileChooser.APPROVE_OPTION) {
        	 			System.out.println("You chose to open this directory: " + filechooser.getSelectedFile().getAbsolutePath());
        	 			
        	 			try
                        {
        	 					image_file = new File(filechooser.getSelectedFile().getAbsolutePath());
        	 					selectedfilestring = (filechooser.getSelectedFile().getAbsolutePath()).toString();
                                image = ImageIO.read(image_file); 
                                image_icon.setIcon(new ImageIcon(image));
                        }
                        catch(Exception c)
                        {
                                JOptionPane.showMessageDialog(null, "Error Reading File","Error", JOptionPane.WARNING_MESSAGE);
                        }
        	 			
        	 		} else {
        	 			System.out.println("Open command cancelled by user.");
        	 		}
                }
            });
            menu.add(openitem);
            
            JMenuItem saveitem = new JMenuItem("Save");
            saveitem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                		final JFileChooser fileChooser = new JFileChooser();
                        int saveValue = fileChooser.showSaveDialog(null);
                        if (saveValue == JFileChooser.APPROVE_OPTION) {
                            try {
                                ImageIO.write(image, "jpg", new File(fileChooser
                                        .getSelectedFile().getAbsolutePath()));
                                JOptionPane.showMessageDialog(null, "File Saved Successfully","Success!", JOptionPane.WARNING_MESSAGE);
                            } catch (IOException r) {
                                r.printStackTrace();
                            }
                        }
                
                
                }
            });
            menu.add(saveitem);
            
            frame.setJMenuBar(menuBar);
            
            // Handler
            GUIEventHandler handler = new GUIEventHandler();
            
            // RGB Button
            rgb_button = new JButton("RGB Colour Balance");
            rgb_button.addActionListener(handler);
            
            // RGB Button
            reset_button = new JButton("Revert To Original");
            reset_button.addActionListener(handler);
            
            // B&W Button
            bandw_button = new JButton("Black And White");
            bandw_button.addActionListener(handler);
            
            // Invert Button
            invert_button = new JButton("Invert");
            invert_button.addActionListener(handler);
            
            // Slow Gamma Button
            slow_gamma_button = new JButton("Slow Gamma");
            slow_gamma_button.addActionListener(handler);
            
            // Fast Gamma Button
            fast_gamma_button = new JButton("Fast Gamma");
            fast_gamma_button.addActionListener(handler);
            
            // Convolve Button
            convolve_button = new JButton("Convolve");
            convolve_button.addActionListener(handler);
            
            // Convolution Button
            convolution_button = new JButton("Convolution");
            convolution_button.addActionListener(handler);
            
            image_icon = new JLabel(new ImageIcon(image));
            
            bandw_button.setBounds(2,2,160,25);
            contentPane.add(bandw_button);
            rgb_button.setBounds(2,30,160,25);
            contentPane.add(rgb_button);
            reset_button.setBounds(164,30,160,25);
            contentPane.add(reset_button);
            invert_button.setBounds(164,2,110,25);
            contentPane.add(invert_button);     
            slow_gamma_button.setBounds(276,2,110,25);
            contentPane.add(slow_gamma_button);
            fast_gamma_button.setBounds(388,2,110,25);
            contentPane.add(fast_gamma_button);
            convolve_button.setBounds(500,2,110,25);
            contentPane.add(convolve_button);
            convolution_button.setBounds(612,2,110,25);
            contentPane.add(convolution_button);
            
            contentPane.add(image_icon);   
            
            // Display the frame
            frame.setVisible(true);        
    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error Reading File","Error", JOptionPane.WARNING_MESSAGE);
        }
        

    }
    
    /*
        This is the event handler for the application
    */
    private class GUIEventHandler implements ActionListener 
    {
       
         public void actionPerformed(ActionEvent event) 
         {
        	 	 /**
                 if (event.getSource()==image_button)
                 {

                        optionPane = new JOptionPane();
                        String input = (String) optionPane.showInputDialog("Enter A File");
                        optionPane.setVisible(true);
                        try
                        {
                                image_file = new File(input);
                                image = ImageIO.read(image_file); 

                                image_icon.setIcon(new ImageIcon(image));
                        }
                        catch(Exception e)
                        {
                                JOptionPane.showMessageDialog(null, "Error Reading File","Error", JOptionPane.WARNING_MESSAGE);
                        }
                 }
                 */
	        	 if (event.getSource()==bandw_button) 
	             {
	                    // Call image processing function
	                    image=BlackAndWhite(image);
	                    // Update image
	                    image_icon.setIcon(new ImageIcon(image));
	             } 
	             else 
            	 if (event.getSource()==rgb_button) 
	             {
	                    // Call image processing function
	                    image=RGBBlackAndWhite(image);
	                    // Update image
	                    image_icon.setIcon(new ImageIcon(image));
	             } 
	             else
            	 if (event.getSource()==reset_button) 
	             {
	                    // Call image processing function
	                    image=RestImage(image);
	                    // Update image
	                    image_icon.setIcon(new ImageIcon(image));
	             } 
	             else 
                 if (event.getSource()==invert_button) 
                 {
                        // Call image processing function
                        image=Invert(image);
                        // Update image
                        image_icon.setIcon(new ImageIcon(image));
                 } 
                 else 
                 if (event.getSource()==slow_gamma_button) 
                 {
                        // Call image processing function
                        image=SlowGamma(image);
                         // Update image
                        image_icon.setIcon(new ImageIcon(image));
                 } 
                 else 
                 if (event.getSource()==fast_gamma_button) 
                 {
                        // Call image processing function
                        image=FastGamma(image);
                        // Update image
                        image_icon.setIcon(new ImageIcon(image));
                 } 
                 else 
                 if (event.getSource()==convolve_button) 
                 {
                        // Call image processing function
                        image=BlueFade(image);
                        // Update image
                        image_icon.setIcon(new ImageIcon(image));
                 }
                 if (event.getSource()==convolution_button)
                 {
                        // Call image processing function
                        image=Convolution(image);
                        // U[date image
                        image_icon.setIcon(new ImageIcon(image)); 
                 }
         }
    }

    /*
        This function will return a pointer to an array
        of bytes which represent the image data in memory.
        Using such a pointer allows fast access to the image
        data for processing (rather than getting/setting
        individual pixels)
    */
    public static byte[] GetImageData(BufferedImage image) 
    {
            WritableRaster WR=image.getRaster();
            DataBuffer DB=WR.getDataBuffer();
            if (DB.getDataType() != DataBuffer.TYPE_BYTE)
            {
                throw new IllegalStateException("That's not of type byte");
            }
            return ((DataBufferByte) DB).getData();
    }

    public BufferedImage RestImage(BufferedImage image) 
    {
    	image_file = new File(selectedfilestring);
    	try {
			image = ImageIO.read(image_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        image_icon.setIcon(new ImageIcon(image));
    	
    	
    	return image;
    }
    /*
        This function shows how to carry out an operation on an image.
        It obtains the dimensions of the image, and then loops through
        the image carrying out the invert. It also shows a faster version
        commented out
    */
    public BufferedImage Invert(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), j;
            //Obtain pointer to data for fast processing
            byte[] data = GetImageData(image);
            
            //Faster version - array index requires no calculation
            for (j=0; j<h*w*3; j++) 
            {
                data[j] = (byte) (255-(data[j]&255));
            }
            return image;
    }
    
    private JOptionPane optionPane; 
    public BufferedImage SlowGamma(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), j;
            //Obtain pointer to data for fast processing
            byte[] data = GetImageData(image);

            double pixel;
            double gamma = 1;
            
            optionPane = new JOptionPane();
            String input = (String) optionPane.showInputDialog("Enter The Gamma");
            optionPane.setVisible(true);
            try
            {
                gamma = Double.parseDouble(input);
                
                for (j=0; j<h*w*3; j++)
                {
                    pixel = (double) (data[j] & 255) / 255;
    
                    data[j] = (byte) (Math.pow(pixel, gamma) * 255);
                }                
              
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "No Value Entered","Error", JOptionPane.WARNING_MESSAGE);
            }
            
            return image;  
        }

    public BufferedImage FastGamma(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), i, j, c;

            //Obtain pointer to data for fast processing
            byte[] data = GetImageData(image);

            int pixel;
            double gamma = 1;
            double loop = 0;
            double corrections [] = new double[256];
            
            optionPane = new JOptionPane();
            String input = (String) optionPane.showInputDialog("Enter The Gamma");
            optionPane.setVisible(true);
            
            try
            {
                gamma = Double.parseDouble(input);
   
                for (j=0; j<256; j++)
                {
                    corrections[j] = (Math.pow((loop / 255), gamma) *255);
                    loop ++;
                }
                
                for (j=0; j<h*w*3; j++)
                {
                    pixel = (data[j] & 255);
                    data[j] = (byte) corrections[pixel];
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "No Value Entered","Error", JOptionPane.WARNING_MESSAGE);
            }
            return image;
    }
    
    private static int colorToRGB(int alpha, int red, int green, int blue) {

    	int newPixel = 0;
    	newPixel += alpha;
    	newPixel = newPixel << 8;
    	newPixel += red; newPixel = newPixel << 8;
    	newPixel += green; newPixel = newPixel << 8;
    	newPixel += blue;

    	return newPixel;

    }
    
    static JSlider getSlider(final JOptionPane optionPane) {

    	final int FPS_MIN = 0;
    	final int FPS_MAX = 255;
    	final int FPS_INIT = 126;
    	
    	JSlider slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        ChangeListener changeListener = new ChangeListener() {
        	
          public void stateChanged(ChangeEvent changeEvent) {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
              optionPane.setInputValue(new Integer(theSlider.getValue()));
            }
          }
          
        };
        slider.addChangeListener(changeListener);
        return slider;
      }
    
    public BufferedImage BlackAndWhite(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), red, green, blue, alpha;
            int newPixel;
            
            
            JOptionPane optionPane = new JOptionPane();
            JSlider slider = getSlider(optionPane);
            optionPane.setMessage(new Object[] { "Select a value: ", slider });
            optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(null, "My Slider");
            dialog.setBounds(200,100,1000,175);
            dialog.setVisible(true);
            

            int[] avgLUT = new int[766];
            // convert each pixel from rgb to grey - (R + G + B)/3
            for(int p=0; p<avgLUT.length; p++) {
            	avgLUT[p] = (int) (p / 3);
            }
            
            try
            {
            	int selectedvalue = (Integer) optionPane.getInputValue();
	            
	            double bandwrange = ((double)selectedvalue / (double) 100);
	            System.out.println("calculated value " + selectedvalue);
	            
	            
            	for(int q=0; q<w; q++) {
                    for(int r=0; r<h; r++) {
             
                        // Get pixels by R, G, B
                        alpha = new Color(image.getRGB(q, r)).getAlpha();
                        red = new Color(image.getRGB(q, r)).getRed();
                        green = new Color(image.getRGB(q, r)).getGreen();
                        blue = new Color(image.getRGB(q, r)).getBlue();
                        
                        int newred = (int) (red * bandwrange);
                        int newgreen = (int) (green * bandwrange);
                        int newblue = (int) (blue * bandwrange);
                        
                        
                        if((newred + newgreen + newblue) > 765){
                        	newred = 255;
                        	newgreen = 255;
                        	newblue = 255;
                        }

                        newPixel = (int) ((newred + newgreen + newblue));
                        // System.out.println("HERE - " + newPixel);
                        newPixel = avgLUT[newPixel];
                        // Return back to original format
                        newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                        
                        // Write pixels into image
                        image.setRGB(q, r, newPixel);
                    }
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.WARNING_MESSAGE);
            }
            return image;
    }
    
    
    static JSlider getSliderRed(final JOptionPane optionPane) {

    	final int FPS_MIN = 0;
    	final int FPS_MAX = 255;
    	final int FPS_INIT = 126;
    	
    	JSlider slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        ChangeListener changeListener = new ChangeListener() {
        	
          public void stateChanged(ChangeEvent changeEvent) {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
              optionPane.setInputValue(new Integer(theSlider.getValue()));
            }
          }
          
        };
        slider.addChangeListener(changeListener);
        return slider;
      }
    static JSlider getSliderGreen(final JOptionPane optionPane) {

    	final int FPS_MIN = 0;
    	final int FPS_MAX = 255;
    	final int FPS_INIT = 126;
    	
    	JSlider slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        ChangeListener changeListener = new ChangeListener() {
        	
          public void stateChanged(ChangeEvent changeEvent) {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
              optionPane.setInputValue(new Integer(theSlider.getValue()));
            }
          }
          
        };
        slider.addChangeListener(changeListener);
        return slider;
      }
    static JSlider getSliderBlue(final JOptionPane optionPane) {

    	final int FPS_MIN = 0;
    	final int FPS_MAX = 255;
    	final int FPS_INIT = 126;
    	
    	JSlider slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        ChangeListener changeListener = new ChangeListener() {
        	
          public void stateChanged(ChangeEvent changeEvent) {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
              optionPane.setInputValue(new Integer(theSlider.getValue()));
            }
          }
          
        };
        slider.addChangeListener(changeListener);
        return slider;
      }
    
    public BufferedImage RGBBlackAndWhite(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), red, green, blue, alpha;
            int newPixel;
            
            
            JOptionPane optionPane = new JOptionPane();
            JSlider redslider = getSliderRed(optionPane);
            JSlider greenslider = getSliderRed(optionPane);
            JSlider blueslider = getSliderRed(optionPane);
            optionPane.setMessage(new Object[] { "Select a Red value: ", redslider, "Select a Green value: ", greenslider, "Select a Blue value: ", blueslider });
            optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(null, "My Slider");
            dialog.setBounds(200,100,1000,400);
            dialog.setVisible(true);
            

            int[] avgLUT = new int[766];
            // convert each pixel from rgb to grey - (R + G + B)/3
            for(int p=0; p<avgLUT.length; p++) {
            	avgLUT[p] = (int) (p / 3);
            }
            
            try
            {
            	int selectedredvalue = (Integer) redslider.getValue();
            	int selectedgreenvalue = (Integer) greenslider.getValue();
            	int selectedbluevalue = (Integer) blueslider.getValue();
            	
            	System.out.println("red " + selectedredvalue + " green " + selectedgreenvalue + " blue " + selectedbluevalue);
	            
	            double redbandwrange = ((double)selectedredvalue / (double) 100);
	            double greenbandwrange = ((double)selectedgreenvalue / (double) 100);
	            double bluebandwrange = ((double)selectedbluevalue / (double) 100);
	            
	            
            	for(int q=0; q<w; q++) {
                    for(int r=0; r<h; r++) {
             
                        // Get pixels by R, G, B
                        alpha = new Color(image.getRGB(q, r)).getAlpha();
                        red = new Color(image.getRGB(q, r)).getRed();
                        green = new Color(image.getRGB(q, r)).getGreen();
                        blue = new Color(image.getRGB(q, r)).getBlue();
                        
                        int newred = (int) (red * redbandwrange);
                        int newgreen = (int) (green * greenbandwrange);
                        int newblue = (int) (blue * bluebandwrange);
                        
                        
                        if((newred + newgreen + newblue) > 765){
                        	newred = 255;
                        	newgreen = 255;
                        	newblue = 255;
                        }

                        // newPixel = (int) ((newred + newgreen + newblue));
                        // System.out.println("HERE - " + newPixel);
                        // newPixel = avgLUT[newPixel];
                        // Return back to original format
                        newPixel = colorToRGB(alpha, newred, newgreen, newblue);
                        
                        // Write pixels into image
                        image.setRGB(q, r, newPixel);
                    }
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.WARNING_MESSAGE);
            }
            return image;
    }
    
    
    
    public BufferedImage BlueFade(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), i, j, c;
            //Obtain pointer to data for fast processing
            byte[] data = GetImageData(image);
            int int_image[][][];
            double t;
            
            int_image = new int[h][w][3];
            
            // Copy byte data to new image taking care to treat bytes as unsigned
            for (j=0; j<h; j++) 
            {
                    for (i=0; i<w; i++) 
                    {
                            for (c=0; c<3; c++) 
                            {
                                    int_image[j][i][c]=data[c+3*i+3*j*w]&255;
                            } // colour loop
                    } // column loop
            } // row loop
            
            // Now carry out processing on this different data typed image (e.g. convolution or "bluefade"
            for (j=0; j<h; j++) 
            {
                    for (i=0; i<w; i++) 
                    {
                                int_image[j][i][0]=255*j/h; //BLUE
                                int_image[j][i][1]=0; //GREEN
                                int_image[j][i][2]=0; //RED
                    } // column loop
            } // row loop
            
            // Now copy the processed image back to the original
            for (j=0; j<h; j++) 
            {
                    for (i=0; i<w; i++) 
                    {
                            for (c=0; c<3; c++) 
                            {
                                    data[c+3*i+3*j*w]=(byte) int_image[j][i][c];
                            } // colour loop
                    } // column loop
            } // row loop
            
            return image;
    }

    public BufferedImage Convolution(BufferedImage image) 
    {
            //Get image dimensions, and declare loop variables
            int w=image.getWidth(), h=image.getHeight(), i, j, c, x, y;
            //Obtain pointer to data for fast processing
            byte[] data = GetImageData(image);
            int int_image[][][];
            int_image = new int[h][w][3];
 
            int min_pixel = 0;
            int max_pixel = 0;
            
            int box_width =0;
            int box_height =0;
            int box [] [] = new int [0][0];

            Object[] possibleValues = { "Box Filter (3x3: All 1's)", 
                                        "Gaussian Filter", 
                                        "High Pass Filter",
                                        "6x4 Filter (All 1's)",
                                        "Custom Filter" };
            Object selectedValue = JOptionPane.showInputDialog(null, "Select A Filter", "Convolution",
                                                               JOptionPane.INFORMATION_MESSAGE, null,
                                                               possibleValues, possibleValues[0]);

            String option = (String) selectedValue;

            if(option.equals("Box Filter (3x3: All 1's)"))
            {
                box_width = 3;
                box_height = 3;
                box = new int [box_width][box_height];
                for(x=0; x<3; x++)
                {
                    for(y=0; y<3; y++)
                    {
                        box[x][y] = 1;
                    }
                }
            }

            if(option.equals("Gaussian Filter"))
            {
                box_width = 3;
                box_height = 3;
                box = new int [box_width][box_height];
                box[0][0] = 1;
                box[0][1] = 3;
                box[0][2] = 1;                
                box[1][0] = 3;
                box[1][1] = 9;
                box[1][2] = 3;
                box[2][0] = 1;                
                box[2][1] = 3;
                box[2][2] = 1;
            }
            
            if(option.equals("High Pass Filter"))
            {
                box_width = 3;
                box_height = 3;
                box = new int [box_width][box_height];
                box[0][0] = -1;
                box[0][1] = -1;
                box[0][2] = -1;                
                box[1][0] = -1;
                box[1][1] = 8;
                box[1][2] = -1;
                box[2][0] = -1;                
                box[2][1] = -1;
                box[2][2] = -1;
            }    
            
            if(option.equals("6x4 Filter (All 1's)"))
            {
                box_width = 6;
                box_height = 4;
                box = new int [box_width][box_height];
                for(x=0; x<6; x++)
                {
                    for(y=0; y<4; y++)
                    {
                        box[x][y] = 1;
                    }
                }
            }            

            if(option.equals("Custom Filter"))
            {
                optionPane = new JOptionPane();
                String input1 = (String) optionPane.showInputDialog("Enter The Box Width");
                String input2 = (String) optionPane.showInputDialog("Enter The Box Height");
                optionPane.setVisible(true);
    
                box_width = Integer.parseInt(input1) ;
                box_height = Integer.parseInt(input2) ;
                box = new int [box_width] [box_height];
    
                for(x=0; x < box_width; x++)
                {
                    for(y=0; y<box_height; y++)
                    {
                        optionPane = new JOptionPane();
                        int input3 = Integer.parseInt(optionPane.showInputDialog("Enter Point " + x + " " + y));
                        optionPane.setVisible(true);
                        box[x][y] = input3;
                    }
                }
            }
            
            int box_left = (box_width-1)/2;
            int box_right = (box_width/2); 
            int box_top = (box_height-1)/2; 
            int box_bottom = (box_height/2);
                
            for (j=box_left; j<h-box_right; j++) 
            {
                    for (i=box_top; i<w-box_bottom; i++) 
                    {
                        for (c=0; c<3; c++)
                        {
                            for (x=-box_left; x<=box_right; x++)
                            {
                                for (y=-box_top; y<=box_bottom; y++)
                                {
                                    int_image[j][i][c] += (data[c+3*(i+x)+3*(j+y)*w]&255) * box[x+box_left][y+box_top]; 
                                }
                            }

                            if(int_image[j][i][c] > max_pixel)
                            {
                                max_pixel = int_image[j][i][c];
                            }
                            if(min_pixel==0)
                            {
                                min_pixel = int_image[j][i][c];
                            }
                            if(int_image[j][i][c] < min_pixel)
                            {
                                min_pixel = int_image[j][i][c];
                            }

                        }
                    } // column loop
            } // row loop
            
            for (j=0; j<h; j++) 
            {
                    for (i=box_top; i<w-box_bottom; i++) 
                    {
                        for (c=0; c<3; c++)
                        {  
                            int_image[j][i][c] = (((int_image[j][i][c] - min_pixel) * 255) / (max_pixel - min_pixel));
                        }
                    }
            }
            
            // Now copy the processed image back to the original
            
            for (j=0; j<h; j++) 
            {
                    for (i=0; i<w; i++) 
                    {
                            for (c=0; c<3; c++) 
                            {
                                    data[c+3*i+3*j*w]=(byte) int_image[j][i][c];
                            } // colour loop
                    } // column loop
            } // row loop  
        
            return image;
    }


    public static void main(String[] args)  
    {
       Image_Editor example = new Image_Editor();
       example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}