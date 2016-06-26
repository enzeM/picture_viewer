class ShortAction implements Action
{
    @Override
    public void execute(ViewerService service, ViewerFrame frame)
    {
        service.shortcut();
    }
}
