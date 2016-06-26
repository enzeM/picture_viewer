import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Component;
import java.io.File;

public class ViewerService
{
    private JFileChooser viewerFileChooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter( "Image files", "jpg", "gif");
    private ArrayList<File> viewerArr;
    private double zoomRange = 0.2;
    public static ViewerService service = null;
    public ViewerService()
    {
        viewerFileChooser.setFileFilter(filter);
        viewerArr = new ArrayList<>();
    }

    public void open(ViewerFrame frame)
    {
        if(viewerFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = viewerFileChooser.getSelectedFile();
            viewerArr.add(selectedFile);
            ImageIcon image = new ImageIcon(selectedFile.getPath());
            frame.getLabel().setIcon(image);
            //reset frame size
            frame.setSize(image.getIconWidth()+200 ,image.getIconHeight()+200);
        }
    }

    //when isEnlarge is true zoom in, else zoom out
    public void zoom(ViewerFrame frame, boolean isEnlarge)
    {
        double enlargeRange = isEnlarge? 1+zoomRange : 1-zoomRange;
        ImageIcon icon = (ImageIcon)frame.getLabel().getIcon();
        if(icon != null)
        {
            int width = (int)(icon.getIconWidth() * enlargeRange);
            int height = (int)(icon.getIconHeight() * enlargeRange);
            ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            frame.getLabel().setIcon(newIcon);
        }
    }

    public void last(ViewerFrame frame)
    {
        //getSelectedFile() return File
        int fileIndex = viewerArr.indexOf(viewerFileChooser.getSelectedFile());
        System.out.println("index "+fileIndex);
        if(fileIndex > 0)
        {
            File file = viewerArr.get(fileIndex - 1);
            ImageIcon lastImage = new ImageIcon(file.getPath());
            frame.getLabel().setIcon(lastImage);
        }
    }

    public void next(ViewerFrame frame)
    {
        int fileIndex = viewerArr.indexOf(viewerFileChooser.getSelectedFile());
        if(fileIndex < viewerArr.size())
        {
            File file = viewerArr.get(fileIndex);
            ImageIcon nextImage = new ImageIcon(file.getPath());
            frame.getLabel().setIcon(nextImage);
        }
    }

    public void exit()
    {
        System.exit(0);
    }

    public void about()
    {
        JOptionPane.showMessageDialog(null, "This image viewer is done by Enze");
    }

    public void shortcut()
    {
        String shortCutMsg = "open file: O \nzoom in image: +\nzoom out image: -\nlast image: <\nnext image: >\n exit: E";
        JOptionPane.showMessageDialog(null, shortCutMsg, "Shortcut", JOptionPane.INFORMATION_MESSAGE, null);
    }

    
    public static ViewerService getInstance()
    {
        if(service == null)
        {
            service = new ViewerService();
        }
        return service;
    }

    public void menuDo(ViewerFrame frame, String cmd)
    {
        if(cmd.equals("Open"))
        {
            open(frame);
        }

        else if(cmd.equals("Larger"))
        {
            zoom(frame, true);
        }

        else if(cmd.equals("Smaller"))
        {
            zoom(frame, false);
        }

        else if(cmd.equals("Last"))
        {
            last(frame);
        }

        else if(cmd.equals("Next"))
        {
            next(frame);
        }

        else if(cmd.equals("Exit"))
        {
            exit();
        }
        
        else if(cmd.equals("Shortcut"))
        {
            String shortCutMsg = "open file: O \nzoom in image: +\nzoom out image: -\nlast image: <\nnext image: >\n exit: E";
            JOptionPane.showMessageDialog(null, shortCutMsg, "Shortcut", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else
        {
            System.out.println("No such command");
        }
    } 
}
