import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewerFrame extends JFrame
{
    private JLabel viewerLabel = new JLabel();
    private ViewerService service = new ViewerService();

    public ViewerFrame()
    {
        init();
    }

    public void init()
    {
        this.setLayout(new BorderLayout());
        createViewerMenuBar();

        JPanel iconPanel = toolButtonPanel();
        this.add(iconPanel, BorderLayout.NORTH);

        JScrollPane viewerPanel = new JScrollPane(viewerLabel);
        this.add(viewerPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JLabel getLabel()
    {
        return this.viewerLabel;
    }

    public void createViewerMenuBar()
    {
        JMenuBar viewerMenuBar = new JMenuBar();
        String[] menuOptions = {"File", "Tool", "Help"};
        String[][] menuItemOptions = {
            {"Open", "Exit"},
            {"Larger", "Smaller", "Last", "Next"},
            {"Shortcut", "About"}
        };

        String[][] actionName = {
            {"OpenAction", "ExitAction"},
            {"LargerAction", "SmallerAction", "LastAction", "NextAction"},
            {"ShortAction", "AboutAction"}};

        for(int i=0; i<menuOptions.length; i++)
        {
            JMenu menu = new JMenu(menuOptions[i]);

            for(int j=0; j<menuItemOptions[i].length; j++)
            {
                ViewerAction action = new ViewerAction(menuItemOptions[i][j], actionName[i][j], this);
                JMenuItem item = new JMenuItem(action);
                menu.add(item);
            }
            //add menus to menu bar
            viewerMenuBar.add(menu);
        }
        //set menu bar to viewer frame
        this.setJMenuBar(viewerMenuBar);
    }

    public JPanel toolButtonPanel()
    {
        JPanel iconPanel = new JPanel();
        String[] iconOptions = {"Open", "Larger", "Smaller", "Last", "Next", "Exit"};
        String[] iconName = {"openFile", "zoomIn", "zoomOut", "last", "next", "exit"};
        String[] actionName = {"OpenAction", "LargerAction", "SmallerAction", "LastAction", "NextAction", "ExitAction"};
        String path = "/Users/enze/Java/viewer/src/icon/";
        String type = ".png";
        for(int i=0; i<iconOptions.length; i++)
        {
            ViewerAction action = new ViewerAction(new ImageIcon(path+iconName[i]+type), actionName[i], this); 
            JButton iconButton = new JButton(action);
            iconPanel.add(iconButton);
        }
        return iconPanel;
    }
}
