package app.team21.risk.test.config;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.test.maps.*;
import app.team21.risk.test.utils.TestUtils;

@RunWith( Suite.class )
@Suite.SuiteClasses( { 
    TestBlankFields.class,
    TestContinentTag.class,
    TestControlValue.class,
    TestControlValue.class,
    TestMapTag.class,
    TestNoContinents.class,
    TestNoCountries.class,
    TestReinforceArmies.class,
    TestTerritoriesTag.class,
    TestValidateContinent.class,
    TestValidateCountry.class,
    TestValidMap.class
} )
public class TestSuiteConfig {

}
