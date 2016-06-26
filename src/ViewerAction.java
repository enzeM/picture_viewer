import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;

public class ViewerAction extends AbstractAction
{
    private ImageIcon icon;
    private String name;
    private String actionName = "";
    private ViewerFrame frame = null;
    private Action action = null;

    public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame)
    {
        super("", icon);
        this.actionName = actionName;
        this.frame = frame;
    }

    public ViewerAction(String name, String actionName, ViewerFrame frame)
    {
        super(name);
        this.actionName = actionName;
        this.frame = frame;
    }

    public ViewerAction()
    {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        ViewerService service = ViewerService.getInstance();
        this.action = getAction(this.actionName);        
        action.execute(service, frame);
    }

    //get action object
    public Action getAction(String actionName)
    {
        try
        {
            if(action == null)
            {
                Action action = (Action)Class.forName(actionName).newInstance();
                this.action = action;
            }
            return this.action;
        }
        catch(Exception e) {return null;}
    }
}
