public class AboutAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.about();
    }
}
