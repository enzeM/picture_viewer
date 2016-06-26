public class OpenAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.open(frame);
    }
}
