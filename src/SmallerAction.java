public class SmallerAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.zoom(frame, false);
    }
}
