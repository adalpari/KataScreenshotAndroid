/*
 * Copyright (C) 2017 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.screenshot;

import static com.karumi.screenshot.ui.view.SuperHeroDetailActivity.SUPER_HERO_NAME_KEY;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.karumi.screenshot.di.MainComponent;
import com.karumi.screenshot.di.MainModule;
import com.karumi.screenshot.model.SuperHero;
import com.karumi.screenshot.model.SuperHeroesRepository;
import com.karumi.screenshot.ui.view.SuperHeroDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class SuperHeroDetailActivityTest extends ScreenshotTest {

  private static final String LONG_NAME = "SuperHero with a really looooong name and even some surnames and family names";
  private static final String REGULAR_NAME = "Superhero name";
  private static final String EMPTY_NAME = "";
  private static final String DESCRIPTION = "Description Super Hero";
  private static final String LONG_DESCRIPTION = "Lorem ipsum dolor sit amet consectetur adipiscing elit, aptent semper purus faucibus posuere facilisis conubia pretium, nostra porta ultrices fringilla magnis ligula. Mauris porttitor vel a interdum nisl vestibulum ante eu varius, cursus tempor netus natoque magna lacinia suspendisse in consequat placerat, integer torquent lectus erat at odio turpis augue. Viverra ad tortor etiam cras euismod ullamcorper rutrum, imperdiet aenean blandit sociosqu nam pellentesque molestie sollicitudin, massa lobortis risus nunc suscipit sapien. Pulvinar vitae elementum auctor ac metus felis habitasse quis per, quisque congue tincidunt hac class mus vulputate potenti pharetra mi, hendrerit quam montes accumsan nec morbi sodales tempus. Dapibus arcu mollis dignissim luctus curae donec curabitur lacus parturient bibendum dui sociis litora fusce dictum fermentum, est vivamus ut et nascetur volutpat nulla dictumst eget facilisi aliquet fames ornare cubilia. Venenatis ultricies taciti sed neque proin himenaeos iaculis urna tellus nisi commodo aliquam duis, dis nibh id maecenas convallis non senectus laoreet cum scelerisque primis rhoncus. Ridiculus mattis diam eleifend egestas nullam eros libero justo leo, tristique penatibus phasellus malesuada habitant vehicula gravida velit feugiat inceptos, praesent condimentum sem sagittis orci platea enim ornare. Molestie diam libero a mus nascetur posuere, eros volutpat aliquet imperdiet varius dapibus nec, natoque primis dis cum rhoncus. Pretium conubia facilisis quisque nunc platea porta praesent, vehicula iaculis morbi pellentesque blandit auctor, ut dignissim sagittis senectus fusce eu. Eget arcu urna sollicitudin sodales metus habitant laoreet tincidunt lectus, egestas mollis himenaeos bibendum augue penatibus nullam felis nostra, curae tempor leo ultricies quis aliquam fames porttitor. In interdum integer dictum vulputate cursus, semper enim id erat, gravida nulla suscipit pharetra. Curabitur velit ridiculus suspendisse risus proin ligula luctus vestibulum fermentum turpis, class congue faucibus sem scelerisque tortor consequat montes cras hendrerit, nibh habitasse cubilia at netus est lacinia lacus vivamus. Mauris nisl inceptos duis litora orci potenti euismod sed rutrum commodo feugiat eleifend phasellus malesuada, mattis vel venenatis hac convallis justo non aptent vitae parturient pulvinar tellus maecenas. Neque elementum purus sociosqu nisi ullamcorper taciti, etiam facilisi per odio magna, dui condimentum sociis fringilla lobortis. Tristique ac ante tempus mi viverra aenean massa magnis, ad placerat dictumst accumsan quam et donec. Torquent ultrices sapien nam nullam parturient sed, velit praesent inceptos tortor nulla porttitor, ut viverra vulputate molestie odio. Scelerisque aliquet nostra leo luctus per lectus, magna turpis tempor diam sociis, vivamus porta taciti placerat integer. Vitae tellus maecenas erat libero metus feugiat semper mauris pharetra, massa aenean nec habitasse sollicitudin cubilia imperdiet dui, posuere mattis penatibus torquent risus etiam lobortis ac. Phasellus natoque nisi ad eu accumsan orci aliquam dictumst, lacinia lacus pulvinar cum in quam rhoncus auctor a, varius proin quisque sapien duis malesuada aptent. Ullamcorper facilisi non ultricies pretium fringilla congue fermentum pellentesque nibh elementum, morbi ornare magnis primis egestas tincidunt urna litora sociosqu potenti, nam montes eros vel et dapibus facilisis tempus habitant. At laoreet fames augue est dis fusce neque, enim eleifend platea felis ridiculus condimentum quis volutpat, conubia himenaeos gravida hac sem netus. Interdum faucibus dignissim donec justo nisl tristique rutrum curabitur arcu purus, consequat senectus cras vehicula commodo ante mollis bibendum class. Cursus venenatis nunc convallis nascetur sagittis sodales blandit mi vestibulum suscipit iaculis mus dictum curae, ligula suspendisse id eget ultrices hendrerit euismod feugiat enim taciti quisque tellus natoque ultrices, bibendum integer lectus suscipit curabitur senectus placerat mauris lacinia a penatibus pulvinar ligula. Hac semper ad iaculis orci pellentesque maecenas vitae mattis mus ornare scelerisque fames, sociosqu facilisis montes ullamcorper himenaeos rhoncus fusce imperdiet eu netus etiam. Dui cras at faucibus consequat condimentum fermentum tincidunt, congue felis nibh porttitor commodo inceptos, malesuada nam neque augue potenti facilisi. Elementum volutpat odio eleifend vehicula gravida tempor arcu, risus vel et nisl urna egestas, nullam vulputate accumsan dictum ultricies mollis. Primis magna per vestibulum sodales nec auctor laoreet dapibus sapien, duis tempus aliquam hendrerit habitant platea sagittis nostra, libero varius leo phasellus aptent conubia dignissim dis. Sollicitudin rutrum erat nulla velit proin suspendisse id pharetra turpis, euismod in dictumst magnis habitasse sem cursus tortor, quam donec sociis vivamus diam est non aliquet. Nascetur massa nunc ante class convallis litora, fringilla mi lacus curae blandit pretium metus, sed viverra lobortis ac luctus. Cubilia eros quis cum parturient venenatis tristique eget molestie ut morbi interdum, posuere torquent purus praesent nisi justo porta aenean ridiculus a mus, sagittis erat ultrices faucibus volutpat lacus at litora cubilia non. Gravida felis penatibus fames etiam dictumst ad lobortis cum vulputate curae, magna molestie ut vitae elementum enim est sociis. Nullam conubia phasellus parturient mauris suspendisse posuere ultricies mollis iaculis scelerisque, vel odio nascetur hendrerit cras varius auctor urna neque, euismod ridiculus nisl nam aliquam maecenas blandit ac vestibulum. Aenean porttitor accumsan senectus velit et sem commodo, arcu feugiat inceptos condimentum hac tellus eu per, tincidunt nisi luctus rhoncus ante imperdiet. Ornare nibh fermentum montes aptent cursus praesent risus natoque torquent augue, vehicula sociosqu quam pellentesque vivamus class facilisis mi ligula convallis, turpis viverra dignissim magnis pretium donec morbi quisque semper. Habitant justo facilisi ullamcorper dapibus nostra mattis purus lectus, diam laoreet id lacinia duis porta eros rutrum, quis placerat himenaeos proin taciti curabitur nunc. Leo orci metus tempor dui congue sodales interdum fringilla bibendum nec, pharetra platea nulla pulvinar venenatis egestas tristique eget integer sollicitudin fusce, tortor sed dictum eleifend tempus habitasse potenti primis dis. Massa sapien netus consequat suscipit libero in aliquet, malesuada varius potenti ad semper pharetra fringilla tempus, erat sodales penatibus posuere volutpat praesent. Vulputate neque at arcu ac eros nam metus malesuada urna class vehicula, sed vestibulum aptent luctus pretium cum orci condimentum odio est porttitor, montes cursus accumsan rutrum netus velit augue eleifend purus tempor. In magnis facilisi blandit sociosqu risus ultrices iaculis consequat, habitasse lacinia ullamcorper parturient massa felis sagittis, viverra himenaeos platea dictum feugiat natoque pellentesque. Congue fusce primis convallis nisl inceptos diam non cubilia curae elementum ut gravida rhoncus dui per, phasellus id nulla taciti lacus nunc nascetur tristique habitant commodo dictumst pulvinar interdum nibh. Morbi sociis bibendum ridiculus tincidunt, aenean laoreet ultricies vel donec, eu vivamus aliquet.";

  @Rule public DaggerMockRule<MainComponent> daggerRule =
      new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
          new DaggerMockRule.ComponentSetter<MainComponent>() {
            @Override public void setComponent(MainComponent component) {
              SuperHeroesApplication app =
                  (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                      .getTargetContext()
                      .getApplicationContext();
              app.setComponent(component);
            }
          });

  @Rule public IntentsTestRule<SuperHeroDetailActivity> activityRule =
      new IntentsTestRule<>(SuperHeroDetailActivity.class, true, false);

  @Mock SuperHeroesRepository repository;

  @Before
  public void setUp() throws Exception {
    SuperHeroDetailActivity.isRunningUITest = true;
  }

  @Test
  public void showAvenger() {
    SuperHero superHero = givenSuperHeroesWith(REGULAR_NAME, DESCRIPTION, true);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showNoAvenger() {
    SuperHero superHero = givenSuperHeroesWith(REGULAR_NAME, DESCRIPTION, false);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showLongNameAvenger() {
    SuperHero superHero = givenSuperHeroesWith(LONG_NAME, DESCRIPTION, true);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showLongNameNoAvenger() {
    SuperHero superHero = givenSuperHeroesWith(LONG_NAME, DESCRIPTION, false);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showEmptyNameAvenger() {
    SuperHero superHero = givenSuperHeroesWith(EMPTY_NAME, DESCRIPTION, true);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showEmptyNameNoAvenger() {
    SuperHero superHero = givenSuperHeroesWith(EMPTY_NAME, DESCRIPTION, false);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showLongDescriptionAvenger() {
    SuperHero superHero = givenSuperHeroesWith(LONG_NAME, LONG_DESCRIPTION, true);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showLongDescriptionNoAvenger() {
    SuperHero superHero = givenSuperHeroesWith(LONG_NAME, LONG_DESCRIPTION, false);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showEmptyDescriptionAvenger() {
    SuperHero superHero = givenSuperHeroesWith(REGULAR_NAME, EMPTY_NAME,true);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showEmptyDescriptionNoAvenger() {
    SuperHero superHero = givenSuperHeroesWith(REGULAR_NAME, EMPTY_NAME,false);

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  @Test
  public void showEmptyCaseForNotFoundSuperhero() {
    SuperHero superHero = givenNoSuperhero();

    Activity activity = startActivity(superHero);

    compareScreenshot(activity);
  }

  private SuperHero givenSuperHeroesWith(String name, String description, boolean avengers) {
    SuperHero superHero = createSuperhero(name, avengers, description);

    when(repository.getByName(anyString())).thenReturn(superHero);

    return superHero;
  }

  @NonNull
  private SuperHero createSuperhero(String name, boolean avengers, String description) {
    String superHeroDescription = description;
    return new SuperHero(name, "https://i.annihil.us/u/prod/marvel/i/mg/9/00/537bcb1133fd7.jpg", avengers, superHeroDescription);
  }

  private SuperHero givenNoSuperhero() {
    SuperHero superHero = createSuperhero(REGULAR_NAME, false, DESCRIPTION);

    when(repository.getByName(anyString())).thenReturn(null);

    return superHero;
  }

  private SuperHeroDetailActivity startActivity(SuperHero superHero) {
    Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), SuperHeroDetailActivity.class);
    intent.putExtra(SUPER_HERO_NAME_KEY, superHero.getName());
    return activityRule.launchActivity(intent);
  }

  @After
  public void tearDown() throws Exception {
    SuperHeroDetailActivity.isRunningUITest = false;
  }
}