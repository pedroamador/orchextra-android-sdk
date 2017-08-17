/*
 * Created by Orchextra
 *
 * Copyright (C) 2017 Gigigo Mobile Services SL
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

package com.gigigo.orchextra.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.gigigo.orchextra.core.domain.entities.Trigger

class TriggerBroadcastReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val trigger = intent.getParcelableExtra<Trigger>(TRIGGER_EXTRA)
    Log.d(TAG, "onTriggerReceived: $trigger")
    TriggerHandlerService.start(context, trigger)
  }

  companion object Navigator {
    private val TAG = "TriggerBR"
    val TRIGGER_RECIVER = "com.gigigo.orchextra.TRIGGER_RECIVER"
    val TRIGGER_EXTRA = "trigger_extra"

    fun getTriggerIntent(trigger: Trigger): Intent {
      val intent = Intent()
      intent.action = TRIGGER_RECIVER
      intent.putExtra(TRIGGER_EXTRA, trigger)
      return intent
    }
  }
}