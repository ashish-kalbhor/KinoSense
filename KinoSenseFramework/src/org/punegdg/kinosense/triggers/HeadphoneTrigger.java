package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * This Trigger is for the action when User plugin the headphones
 * 
 * @author "Amruta Deshpande"<deshpande.amruta22@gmail.com>
 * 
 */

public class HeadphoneTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{
	/**
	 * Android's Application Context
	 */
	private Context context = null;


	public void onCreate(Context context)
	{
		this.context = context;
		IntentFilter inf = new IntentFilter();
		inf.addAction(Intent.ACTION_HEADSET_PLUG);
		context.registerReceiver(getBroadCastReceiver(), inf);
	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		return this;
	}


	public void onDestroy()
	{
		context.unregisterReceiver(getBroadCastReceiver());
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{

		if ( intent.getExtras().getInt("state") == 0 )
		// Headset Disconnected
		{
			Intent bcHIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcHIntent.putExtra("trigger", "HEADSET_DISCONNECTED");
			context.sendBroadcast(bcHIntent);

		}
		else
		// Headset Connected
		{
			Intent bcHIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcHIntent.putExtra("trigger", "HEADSET_CONNECTED");
			context.sendBroadcast(bcHIntent);
		}
	}

}
