package net.runelite.client.plugins.ironmanplanner;

import com.google.inject.Provides;
import javax.inject.Inject;

import java.awt.image.BufferedImage;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
        name = "Ironman Planner",
        description = "Guides Ironman players through a structured progression plan.",
        tags = {"ironman", "planner", "guide"}
)
public class IronmanPlannerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private ConfigManager configManager;

    private IronmanPlannerPanel panel;
    private NavigationButton navButton;
    private TaskManager taskManager;

    @Override
    protected void startUp()
    {
        taskManager = new TaskManager(configManager);
        panel = new IronmanPlannerPanel(taskManager);

        BufferedImage icon = ImageUtil.loadImageResource(getClass(), "Boaty 32.png");

        navButton = NavigationButton.builder()
                .tooltip("Ironman Planner")
                .icon(icon)
                .panel(panel)
                .priority(5)
                .build();

        clientToolbar.addNavigation(navButton);
        log.info("Ironman Planner started.");
    }

    @Override
    protected void shutDown()
    {
        clientToolbar.removeNavigation(navButton);
        log.info("Ironman Planner stopped.");
    }

    @Provides
    IronmanPlannerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(IronmanPlannerConfig.class);
    }
}
