package net.runelite.client.plugins.ironmanplanner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task
{
    private String id;
    private String name;
    private String description;
    private boolean completed;
}
