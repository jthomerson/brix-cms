package brix.plugin.site.page;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import brix.Brix;
import brix.jcr.wrapper.BrixNode;
import brix.plugin.site.NodeConverter;
import brix.plugin.site.SimpleCallback;
import brix.plugin.site.page.admin.CreatePageOrTemplatePanel;

public class PageSiteNodePlugin extends AbstractSitePagePlugin
{

    public static final String TYPE = Brix.NS_PREFIX + "tilePage";

    public PageSiteNodePlugin(Brix brix)
    {
        super(brix);
    }

    @Override
    public NodeConverter getConverterForNode(BrixNode node)
    {
        if (TemplateSiteNodePlugin.TYPE.equals(((BrixNode)node).getNodeType()))
            return new FromTemplateConverter(getNodeType());
        else
            return super.getConverterForNode(node);
    }

    private static class FromTemplateConverter extends SetTypeConverter
    {
        public FromTemplateConverter(String type)
        {
            super(type);
        }
    };

    @Override
    public Panel<?> newCreateNodePanel(String id, IModel<BrixNode> parentNode, SimpleCallback goBack)
    {
        return new CreatePageOrTemplatePanel(id, parentNode, getNodeType(), goBack);
    }

    @Override
    public String getNodeType()
    {
        return TYPE;
    }

    public String getName()
    {
        return "Page";
    }
    
    public IModel<String> newCreateNodeCaptionModel(IModel<BrixNode> parentNode)
    {
    	return new Model<String>("Create New Page");
    }
}