public class ExitAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.exit();
    }
}
