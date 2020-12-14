package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class LifetimeLimitedGameObject extends GameObject {
    private final LocalDateTime bornAt;

    protected LifetimeLimitedGameObject(Position position) {
        this.position = position;
        this.bornAt = LocalDateTime.now();
    }

    public long getAge() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.MILLIS.between(this.bornAt, now);
    }
}
