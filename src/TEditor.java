import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.JColorChooser;


public class TEditor extends JFrame{
    
    private final JTextPane textArea;
    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuBar menuBar;
    private final StyledEditorKit styledEditorKit;
    private JButton button;
    private JToolBar toolBar;
    private JScrollPane scrollPane;
    private ImageIcon img;
    private Color color = (Color.WHITE);
    
    public TEditor(){
    
        super("Text Editor Plus Plus");
        
        
        textArea = new JTextPane();
        
        scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
       styledEditorKit = new StyledEditorKit();
       textArea.setEditorKit(styledEditorKit);
        
       menuBar = new JMenuBar();
       menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

       toolBar = new JToolBar();
       toolBar.setBorder(new EtchedBorder());
       toolBar.setRollover(true);
       toolBar.setFloatable(false);
    openSave();
    setFontSize(); 
    setTextBold();
    setTextItalic();
    setTextUnderLine();
    setFontType();
    setTextRightAlign();
    setTextCenterAlign();
    setTextLeftAlign();
    setBackgroundColor();
    setTextColor();
    copy();
    paste();
    cut();
  
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(400, 100));
    panel.add(toolBar, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    setContentPane(panel);
    
   
    }
    
    private void setFontType(){
    
        menu = new JMenu("Font Type");
        
       String fonts[] = 
       GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String x : fonts){
       
        menuItem = new JMenuItem("" + x);
        menuItem.addActionListener(new StyledEditorKit.FontFamilyAction(null, x));
            
        menu.add(menuItem);
        
        }
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setContentPane(scrollPane);
    
    
    
    }
    
    private void setFontSize(){
    
        menu = new JMenu("Font");
        
        for(int i = 8 ; i<=48 ; i+=2){
       
        menuItem = new JMenuItem("" + i);
        menuItem.addActionListener(new StyledEditorKit.FontSizeAction("" + i, i));
            
        menu.add(menuItem);
        
        }
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setContentPane(scrollPane);
        
    
    }
    
    private void setTextBold(){
        
        button = new JButton();
        img = new ImageIcon(getClass().getResource("bold.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.BoldAction());
    
    }
    
    private void setTextItalic(){
    
        button = new JButton();
        img = new ImageIcon(getClass().getResource("italic.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.ItalicAction());
 
    }

    private void setTextUnderLine(){
        button = new JButton();
        img = new ImageIcon(getClass().getResource("underline.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.UnderlineAction());
    
    
    }
    
    private void setTextRightAlign(){
        button = new JButton();
        img = new ImageIcon(getClass().getResource("right.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.AlignmentAction("Align Right", StyleConstants.ALIGN_RIGHT));
    
    
    }
  
    private void setTextLeftAlign(){
    
        button = new JButton();
        img = new ImageIcon(getClass().getResource("left.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.AlignmentAction("Align Left", StyleConstants.ALIGN_LEFT));
    
    
    }
       
    private void setTextCenterAlign(){
    
        button = new JButton();
        img = new ImageIcon(getClass().getResource("center.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(new StyledEditorKit.AlignmentAction("Align Center", StyleConstants.ALIGN_CENTER));
    
    
    }
    
    private void setBackgroundColor(){
    
       
        textArea.setBackground(color);
        
        button = new JButton();
        img = new ImageIcon(getClass().getResource("background.png"));
        button.setIcon(img);
        toolBar.add(button);
        toolBar.addSeparator();
        
        button.addActionListener(
        
                new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent event){
        
                        color = JColorChooser.showDialog(null, "Select Background Color", color);
                        if(color == null)
                            color = (Color.WHITE);
                        
                        
                        textArea.setBackground(color);
                    }
                }
        
        
        );
        
    
    }
    
    private void setTextColor(){
       
       button  = new JButton();
        img = new ImageIcon(getClass().getResource("textcolor.png"));
        button.setIcon(img);
        
        toolBar.add(button);
        toolBar.addSeparator();

        button.addActionListener(
        
                new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent event){
        
                      
                        
                        color = JColorChooser.showDialog(null, "Select Text Color", color);
                        
                        if(color == null)
                            color = (Color.BLACK);
                        
                        SimpleAttributeSet attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, color);
                        textArea.setCharacterAttributes(attr, false);
                    
                    }
                }
        
        
        );

    
    }
    
    
    private void copy(){
    
        button = new JButton("copy");
//        img = new ImageIcon(getClass().getResource("copy.png"));
  //      button.setIcon(img);
        
        toolBar.add(button);
        toolBar.addSeparator();

        button.addActionListener(new StyledEditorKit.CopyAction());
        
    }
    
    
    
    private void paste(){
    
        button = new JButton("paste");
//        img = new ImageIcon(getClass().getResource("paste.png"));
  //      button.setIcon(img);
        
        toolBar.add(button);
        toolBar.addSeparator();

        button.addActionListener(new StyledEditorKit.PasteAction());
        
    }
 

    private void cut(){
    
        button = new JButton("cut");
//        img = new ImageIcon(getClass().getResource("paste.png"));
  //      button.setIcon(img);
        
        toolBar.add(button);
        toolBar.addSeparator();

        button.addActionListener(new StyledEditorKit.CutAction());
        
    }
  
 private void saveOpen() {
        
        JButton saveBtn = new JButton("Save");
        JButton openBtn = new JButton("Open");
        
        menuBar.add(saveBtn);
        menuBar.add(openBtn);
        
        
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser saveFile = new JFileChooser();
                
                saveFile.showSaveDialog(null);
            }
        });

        openBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser openFile = new JFileChooser();
                openFile.showOpenDialog(null);
            }
        });

    }
  
    
    private void openSave(){
        
        /*menu = new JMenu();
        JMenuItem open = new JMenuItem("Open");
        menu.add(open);
        
        JMenuItem save = new JMenuItem("Save");
        menu.add(save);
        
        menuBar = new JMenuBar();
        menuBar.add(menu);
                
        setJMenuBar(menuBar);
        setContentPane(scrollPane);
        */
        JButton save = new JButton("Save");
        JButton open = new JButton("Open");
        
        menuBar.add(save);
        menuBar.add(open);
        open.addActionListener(
        
                new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent event){
                    //TEditor app = new TEditor();
        
                        Object obj = event.getSource();
                        
                        JFileChooser chooser = new JFileChooser() ;
                       
                        if(obj == open)
                        if( chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ){
                        try{
                           FileInputStream fis = new FileInputStream( chooser.getSelectedFile() ) ;
                           BufferedReader br  = new BufferedReader(  new InputStreamReader( fis ) ) ;
                           String read ;
                           StringBuffer text = new StringBuffer() ;
                           while( ( read = br.readLine() ) != null ) 
                           {
                           text.append( read ).append( "\n" ) ;
                           }
                           textArea.setText( text.toString() ) ;
                        
                        
                        }
                        catch( IOException e ) 
                        {
                            JOptionPane.showMessageDialog( null , 
                            "Error in File Operation" ,
                            "Error in File Operation" , 
                            JOptionPane.INFORMATION_MESSAGE) ;
                        }
                        
                        }
                        
                        
                        else if(obj == save)
                        if( chooser.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ){
                        try{
                        File file = new File(chooser.getDialogTitle());
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(textArea.getText());
			
			fileWriter.flush();
			fileWriter.close();
                        
                        }
                        catch( IOException e ) 
                        {
                            JOptionPane.showMessageDialog( null , 
                            "Error in File Operation" ,
                            "Error in File Operation" , 
                            JOptionPane.INFORMATION_MESSAGE) ;
                        }
                        
                        }
                    }
                
                }
        
        
        );
        
        
        
 
    }
    
    
}
