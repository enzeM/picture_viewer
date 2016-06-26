public class LastAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.last(frame);
    }
}
