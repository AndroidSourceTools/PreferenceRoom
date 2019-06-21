/*
 * Copyright (C) 2017 skydoves
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

package com.skydoves.preferenceroomdemo.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.google.gson.Gson;
import com.skydoves.preferenceroomdemo.models.Pet;
import com.skydoves.preferenceroomdemo.models.PrivateInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/** Developed by skydoves on 2017-11-29. Copyright (c) 2017 skydoves rights reserved. */
@RunWith(AndroidJUnit4.class)
public class ProfileEntityTest {

  private Preference_UserProfile profile;
  private SharedPreferences preferences;

  @Before
  public void getProfileEntityInstance() {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    profile = Preference_UserProfile.getInstance(appContext);
    preferences = appContext.getSharedPreferences(profile.getEntityName(), Context.MODE_PRIVATE);
  }

  @Test
  public void preferenceTest() {
    profile.clear();
    preferences.edit().putString(profile.nicknameKeyName(), "PreferenceRoom").apply();
    preferences.edit().putBoolean(profile.LoginKeyName(), true).apply();
    preferences.edit().putInt(profile.visitsKeyName(), 12).apply();

    assertThat(
        preferences.getString(profile.nicknameKeyName(), null) + "!!!", is(profile.getNickname()));
    assertThat(preferences.getBoolean(profile.LoginKeyName(), false), is(profile.getLogin()));
    assertThat(preferences.getInt(profile.visitsKeyName(), -1), is(profile.getVisits()));
  }

  @Test
  public void defaultTest() throws Exception {
    profile.clear();
    assertThat(profile.getNickname(), is("skydoves!!!"));
    assertThat(profile.getLogin(), is(false));
    assertThat(profile.getVisits(), is(1));
    assertThat(profile.getUserinfo().getName(), is("null"));
    Assert.assertNull(profile.getUserPet());
  }

  @Test
  public void putPreferenceTest() throws Exception {
    profile.clear();
    profile.putNickname("PreferenceRoom");
    profile.putLogin(true);
    profile.putVisits(12);
    profile.putUserinfo(new PrivateInfo("Jaewoong", 123));

    assertThat(profile.getNickname(), is("Hello, PreferenceRoom!!!"));
    assertThat(profile.getLogin(), is(true));
    assertThat(profile.getVisits(), is(13));
    assertThat(profile.getUserinfo().getName(), is("Jaewoong"));
    assertThat(profile.getUserinfo().getAge(), is(123));
  }

  @Test
  public void baseGsonConverterTest() throws Exception {
    Gson gson = new Gson();
    Pet pet = new Pet("skydoves", 11, true, Color.WHITE);

    profile.putUserPet(pet);
    assertThat(preferences.getString(profile.userPetKeyName(), null), notNullValue());

    Pet petFromPreference =
        gson.fromJson(preferences.getString(profile.userPetKeyName(), null), Pet.class);
    assertThat(petFromPreference.getName(), is("skydoves"));
    assertThat(petFromPreference.getAge(), is(11));
    assertThat(petFromPreference.isFeed(), is(true));
    assertThat(petFromPreference.getColor(), is(Color.WHITE));

    assertThat(profile.getUserPet().getName(), is("skydoves"));
    assertThat(profile.getUserPet().getAge(), is(11));
    assertThat(profile.getUserPet().isFeed(), is(true));
    assertThat(profile.getUserPet().getColor(), is(Color.WHITE));
  }

  @Test
  public void keyNameListTest() throws Exception {
    Assert.assertEquals(profile.getkeyNameList().size(), 5);
  }
}
