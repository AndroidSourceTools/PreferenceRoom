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

package com.skydoves.preferenceroomdemo.components

import com.skydoves.preferenceroom.PreferenceComponent
import com.skydoves.preferenceroomdemo.LoginActivity
import com.skydoves.preferenceroomdemo.MainActivity
import com.skydoves.preferenceroomdemo.entities.Device
import com.skydoves.preferenceroomdemo.entities.Profile

/**
 * Developed by skydoves on 2017-11-20.
 * Copyright (c) 2017 skydoves rights reserved.
 */

/**
 * Component integrates entities.
 */
@PreferenceComponent(entities = [Profile::class, Device::class])
interface UserProfileComponent {
  /**
   * declare dependency injection targets.
   */
  fun inject(target: MainActivity)
  fun inject(target: LoginActivity)
}
