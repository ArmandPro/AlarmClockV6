package com.example.etudes.alarmclockv6.services.modeles;

import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.SuccessService;

/**
 * Created by Florian on 18/11/2017.
 */
public class Success {
    private String id;
    private String name, description;
    private int reward, advancement;
    private boolean finished;

    public Success(String id, String name, String description, int advancement, int reward, boolean finished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.advancement = advancement;
        this.finished = finished;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        SuccessService.getInstance().updateSuccess(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        SuccessService.getInstance().updateSuccess(this);
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
        SuccessService.getInstance().updateSuccess(this);
    }

    public int getAdvancement() {
        return advancement;
    }

    public void setAdvancement(int advancement) {
        this.advancement = advancement;
        SuccessService.getInstance().updateSuccess(this);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        SuccessService.getInstance().updateSuccess(this);
        if(finished) HabitsService.getInstance().getHabits().addRewardTime(reward);
    }
}
