package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MissionControlTest {
    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;
    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;
    private static final Double ESCORT_MISSION_RATING_MAXIMUM_VALUE = 75D;
    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;
    private static final Double ESCORT_BOUNTY_MAXIMUM_VALUE = 125000D;

    private static final String MISSION_ID_TEST_VALUE = "12345678";
    private static final Double MISSION_RATING_TEST_VALUE = 20D;
    private static final Double MISSION_BOUNTY_TEST_VALUE = 5000D;

    private MissionControl missionControl;

    @Before
    public void init() {
        this.missionControl = new MissionControlImpl();
    }

    @Test
    public void generateMissionWithLongId() {
        String missionId = "123456789";

        Mission mission = this.missionControl.generateMission(missionId, MISSION_RATING_TEST_VALUE, MISSION_BOUNTY_TEST_VALUE);

        Assert.assertEquals(MISSION_ID_MAXIMUM_LENGTH, Integer.valueOf(mission.getId().length()));
    }

    @Test
    public void generateMissionWithHigherRating() {
        Double rating = 110D;

        Mission mission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, rating, MISSION_BOUNTY_TEST_VALUE);

        Assert.assertEquals(ESCORT_MISSION_RATING_MAXIMUM_VALUE, mission.getRating());
    }

    @Test
    public void generateMissionWithLowerRating() {
        Double rating = -10D;

        Mission mission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, rating, MISSION_BOUNTY_TEST_VALUE);

        Assert.assertEquals(MISSION_RATING_MINIMUM_VALUE, mission.getRating());
    }

    @Test
    public void generateMissionWithHigherBounty() {
        Double bounty = 110000D;

        Mission mission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, MISSION_RATING_TEST_VALUE, bounty);

        Assert.assertEquals(ESCORT_BOUNTY_MAXIMUM_VALUE, mission.getBounty());
    }

    @Test
    public void generateMissionWithLowerBounty() {
        Double bounty = -10D;

        Mission mission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, MISSION_RATING_TEST_VALUE, bounty);

        Assert.assertEquals(MISSION_BOUNTY_MINIMUM_VALUE, mission.getBounty());
    }

    @Test
    public void checkGeneratedMissionType() {
        String escortMissionClassName = "EscortMission";
        String huntMissionClassName = "HuntMission";
        String surveillanceMissionClassName = "SurveillanceMission";

        Mission escortMission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, MISSION_RATING_TEST_VALUE, MISSION_BOUNTY_TEST_VALUE);
        Mission huntMission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, MISSION_RATING_TEST_VALUE, MISSION_BOUNTY_TEST_VALUE);
        Mission surveillanceMission = this.missionControl.generateMission(MISSION_ID_TEST_VALUE, MISSION_RATING_TEST_VALUE, MISSION_BOUNTY_TEST_VALUE);

        Assert.assertTrue(escortMissionClassName.equals(escortMission.getClass().getSimpleName()));
        Assert.assertTrue(huntMissionClassName.equals(huntMission.getClass().getSimpleName()));
        Assert.assertTrue(surveillanceMissionClassName.equals(surveillanceMission.getClass().getSimpleName()));
    }
}
