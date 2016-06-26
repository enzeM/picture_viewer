public class LargerAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.zoom(frame, true);
    }
}
