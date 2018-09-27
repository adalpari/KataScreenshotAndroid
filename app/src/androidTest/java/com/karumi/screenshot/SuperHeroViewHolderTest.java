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

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import static org.mockito.Mockito.mock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.view.LayoutInflater;
import android.view.View;

import com.karumi.screenshot.model.SuperHero;
import com.karumi.screenshot.ui.presenter.SuperHeroesPresenter;
import com.karumi.screenshot.ui.view.SuperHeroViewHolder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SuperHeroViewHolderTest extends ScreenshotTest {

  @Before
  public void setUp() throws Exception {
    SuperHeroViewHolder.isRunningUITest = true;
  }

  @Test public void showsSuperHero() {
    SuperHero superHero = givenASuperHero();
    SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero, InstrumentationRegistry.getTargetContext());

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  @Test public void showsAvengerSuperHero() {
    SuperHero superHero = givenASuperHero();
    SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero, InstrumentationRegistry.getTargetContext());

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  @Test public void showsrSuperHeroLongName() {
    SuperHero superHero = givenASuperHeroWithALongName();
    SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero, InstrumentationRegistry.getTargetContext());

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  private SuperHeroViewHolder givenASuperHeroViewHolder() {
    Context context = getInstrumentation().getTargetContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.super_hero_row, null, false);
    return new SuperHeroViewHolder(view, mock(SuperHeroesPresenter.class));
  }

  private SuperHero givenASuperHeroWithALongDescription() {
    String superHeroName = "Super Hero Name";
    String superHeroDescription =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
            + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
            + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
            + "mollit anim id est laborum.";
    boolean isAvenger = false;
    return givenASuperHero(superHeroName, superHeroDescription, isAvenger);
  }

  private SuperHero givenASuperHeroWithALongName() {
    String superHeroName =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
            + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
            + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
            + "mollit anim id est laborum.";
    String superHeroDescription = "Description Super Hero";
    boolean isAvenger = false;
    return givenASuperHero(superHeroName, superHeroDescription, isAvenger);
  }

  private SuperHero givenAnAvenger() {
    return givenASuperHero("Super Hero Name", "Super Hero Description", true);
  }

  private SuperHero givenASuperHero() {
    return givenASuperHero("Super Hero Name", "Super Hero Description", false);
  }

  private SuperHero givenASuperHero(String superHeroName, String superHeroDescription,
      boolean isAvenger) {
    return new SuperHero(superHeroName, null, isAvenger, superHeroDescription);
  }

  @After
  public void tearDown() throws Exception {
    SuperHeroViewHolder.isRunningUITest = false;
  }
}