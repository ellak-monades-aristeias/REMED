 

 

*Project Title*: **RE**mind me my **MED**ications)

Project Acronym: **REMED**

 

 

 

Παραδοτέο: Σχεδιασμός για smartphone και smart-watch (περιορισμοί λόγω της
μικρής οθόνης) – ορισμός flow of information

Deliverable: Design for smartphone and smart-watch –Flow of information
definition

 

 

 

 

 

 

 

Funded by Μονάδες Αριστείας Ανοιχτού Λογισμικού (ma.ellak.gr)

 

 

Released 30-08-2015

 

 

**Contents**
============

 

[Executive Summary. 3](<#_Toc428537098>)

[Designing Effective Navigation for android apps. 4](<#_Toc428537099>)

[Desired functionalities. 4](<#_Toc428537100>)

[Diagram Screen Relationships. 4](<#_Toc428537101>)

[How information in different screens interacts. 5](<#_Toc428537102>)

[Home. 6](<#_Toc428537103>)

[Menu. 7](<#_Toc428537104>)

[Medications screen. 8](<#_Toc428537105>)

[Add medication. 9](<#_Toc428537106>)

[Medication name. 9](<#_Toc428537107>)

[Reminder Times. 10](<#_Toc428537108>)

[Schedule. 10](<#_Toc428537109>)

[Shape and colour. 11](<#_Toc428537110>)

[Dosage. 11](<#_Toc428537111>)

[Instructions. 12](<#_Toc428537112>)

[Missed Med. 13](<#_Toc428537113>)

[Reports. 14](<#_Toc428537114>)

[Settings. 15](<#_Toc428537115>)

[Medications reminders. 15](<#_Toc428537116>)

[Sound. 15](<#_Toc428537117>)

[Help. 16](<#_Toc428537118>)

[Design Principles for Android Wear. 17](<#_Toc428537119>)

[Bridged Notifications. 17](<#_Toc428537120>)

[Creating a Notification for Wearables. 18](<#_Toc428537121>)

[Import the necessary classes. 18](<#_Toc428537122>)

[Create Notifications with the Notification Builder. 18](<#_Toc428537123>)

[Add Action Buttons. 19](<#_Toc428537124>)

[Specify Wearable-only Actions. 19](<#_Toc428537125>)

[Add a Big View.. 20](<#_Toc428537126>)

[Deliver the Notification. 21](<#_Toc428537127>)

 

 

Executive Summary
=================

 

In this deliverable the design of the REMED mobile app that aims to increase
patients’ adherence to the prescribed medications by constantly reminding them
to adhere even in their smart-watches. The identified screens of the Android
mobile app (with illustrative screenshopts), as well as their interactions and
relationships, are described. Moreover, the contents of each screen are
presented in detail and all identified drop down lists are included in the
deliverable.

The main Design Principles for Android Wear as well as the coding needed for
implementing notifications in android wear are also presented.

 

 

  


 

Designing Effective Navigation for android apps
===============================================

 

Desired functionalities
-----------------------

The user should be able to:

1.        Add a profile the after app installation

2.       Edit the profile

3.       View the virtual pillbox for instant overview of next due medication
(with an intuitive interface)

4.      Add/ edit/ remove medications (with drop down lists and simple graphics)

5.       Be reminded to take the medications

6.      Be notified about missed medications

7.       Get reports for daily/ weekly/ monthly adherence

8.      Edit settings, mainly concerning reminders/ notifications, which will be
provided also by the smart-watch

 

 

Diagram Screen Relationships
----------------------------

Below we define the directed relationships between the main screens; an arrow
from one screen A to another screen B implies that screen B should be directly
reachable via some user interaction in screen A. More screens are presented in
the next sections in order to provide the necessary functionality and include
the information needed for easy navigation.

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image002.png>)

 

 

How information in different screens interacts
----------------------------------------------

In the diagram below the interactions between screens and the information
available in these screens are depicted.

For instance when the user adds a new medication the following possibilities are
anticipated:

·         In the virtual pillbox the new medication (dosage, timing etc.) should
appear

·         New reminders are anticipated in the scheduled timing

·         New missed medications are provided according to user’s adherence

Settings are also important since depending on them notifications and reminders
can be adjusted.

Reports expose patient’s adherence to the prescribed medications.

Info about med presents current user settings for a medication and add med
enables the user to add newly prescribed medications.

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image004.png>)

 

 

Home Screen
-----------

In the home screen (depicted in figure 1 below) the main elements should be:

·         The action bar (technical details can be found in
<http://developer.android.com/guide/topics/ui/actionbar.html> )

·         The pillbox or medications chart, i.e. a graphic that enables the user
immediately check next medication due as well as for the whole day

·         The add medications button which enables the user add medications, as
well as remove or edit their details

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image006.png>)

Figure 1: Home screen

 

  


 

Menu
----

In the menu all screens should be included (as depicted in figure 2) , apart
from the reminders that are much more a functionality interrelated with most
screens than a screen itself.

In the following these screens and what functionality should be provided by them
will be thoroughly presented.

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image008.png>)

Figure 2: Menu screen

  


 

Medications screen
------------------

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image010.png>)

Figure 3: Medications screen

 

  


 

Add medication
--------------

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image012.png>)

Figure 4: Add medication screens with all the choices the user should have

 

 

### Medication name

From the website of SFEE (Σύνδεσμος Φαρμακευτικών Επιχειρήσεων Ελλάδος)
<https://www.sfee.gr/deltio-timon-farmakon-anthropinis-chrisis-20-7-2015/> as
well as from the website of the Greek Ministry of Health
<http://www.moh.gov.gr/articles/times-farmakwn/deltia-timwn/3386-deltio-timwn-farmakwn-anthrwpinhs-xrhshs-20-7-2015>
we were able to download (in excel format) the list with all commercially
available medications in Greece (more than 10.000).

From this file we can extract the names, type (syrup, tablet etc.), content and
drastic substance and thus the user will be able to choose from a structured
list and not write free text.

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image014.jpg>)

Figure 5: List with commercially available medications in Greece

 

 

### Reminder Times

The user should be able to choose the frequency of each medication from a drop
down list that includes fields such as:

·         Once a day

·         Twice a day

·         3 times a day

·         4 times a day

·         5 times a day

·         6 times a day

·         7 times  a day

·         8 times  a day

Then depending in what he chose there will appear the respective number of
reminder times and he the user will be able to set the exact time for each
reminder as well as the quantity (dosage).

 

### Schedule

The user should be able to:

·         Choose the start date from a calendar

·         Define the duration which can be continuous or a number of days

·         Set if the medication should be taken every day or specific days of
the week

 

### Shape and colour

The user should be able to choose from a gallery that includes several types of
medications as those presented in the figure below.

What is needed are two galleries one with different shapes and a second with the
colors.

![Description: Colorful Capsules & Pills Vector](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image015.jpg>)

 

### Dosage

Apart from the quantity (i.e. a number) there should be a drop down list that
will include:

·         Pills

·         Cc

·         Ml

·         Gr

·         Mg

·         Drops

·         Pieces

·         Puffs

·         Units

·         Teaspoon

·         Tablespoon

·         Patch

·         Mcg

·         Iu

·         meq

 

### Instructions

Apart from free text instructions the user should be able to set food
instructions choosing from a simple list:

·         Before food

·         With food

·         After food

 

  


 

Missed Med
----------

Accessible also from the action bar of the home screen the Missed med screen
should inform the user about any missed medications.

Managing missed medications is a complicated issue for which only pharmacists
and prescribing clinicians can give reliable instructions to the users and the
scope of this screen is only to inform the user and not advise him/ her what to
do with missed dosages.

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image017.png>)

 

 

Reports
-------

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image019.png>)

 

  


 

Settings
--------

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image021.png>)

 

### Medications reminders 

The user should be able to set:

·         Max reminders per medication (number)

·         Snooze duration (minutes)

·         Pop up notification (no pop up, when screen is on, always show pop up)

·         Reminder text (e.g. it is time to take your meds)

### Sound

The user should be able to set:

·         The volume level

·         Pick a ringtone

 

 

 

Help
----

 

![](<file:///C:\Users\medlab1\AppData\Local\Temp\msohtmlclip1\01\clip_image023.png>)

 

 

  


 

Design Principles for Android Wear[[1]](<#_ftn1>)
=================================================

 

Focus on not stopping the user and all else will follow: A watch is a perfect
form factor for a device that you can use while doing something else, such as
cooking, eating, walking, running, or even having a conversation. If using your
wearable app causes the user to stop whatever they’re doing, it’s a good
occasion to consider how to improve it using the principles in this section.

Design for big gestures: Use few and large touch targets. When you swipe through
photos on your phone, you’re using a large area of the display and precision
isn't required. That’s the best kind of interaction for a wearable device. Your
users are going to use your app in all sorts of situations, the least frequent
one might actually be sitting down at their desk.

Think about stream cards first: An app that offers to check in users could
appear in the stream suggesting the most likely place nearby, after a certain
amount of time.The best experience on a wearable device is when the right
content is there just when the user needs it. You can figure out when to show
your cards with sensors, or events happening in the cloud. For the cases where
it’s impossible to know when the user needs your app, you can rely on a voice
action or touch.

Do one thing, really fast: While users will engage with your app for only a few
seconds at time, they'll use it many times throughout the day. A well-designed
stream card carries one bit of information and potentially offers a few action
buttons when the user swipes over.

Design for the corner of the eye: The longer the user is looking at your app,
the more you are pulling them out of the real world. Thinking about how to
design your app for glance ability can vastly help the user get full value from
your app and quickly go back to what they were doing.

 

 

 

 

Bridged Notifications
---------------------

This is the simplest way to get on Android Wear. In fact, your app already does
this if it uses notifications. You can add Wear-specific features like extra
pages and voice replies by using the new notification APIs.

  


 

Creating a Notification for Wearables[[2]](<#_ftn2>)
----------------------------------------------------

To build handheld notifications that are also sent to wearables,
use [NotificationCompat.Builder](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html>).
When you build notifications with this class, the system takes care of
displaying notifications properly, whether they appear on a handheld or
wearable.

 

### Import the necessary classes

To import the necessary packages, add this line to yourbuild.gradlefile:

compile "com.android.support:support-v4:20.0.+"

Now that your project has access to the necessary packages, import the necessary
classes from the support library:

import android.support.v4.app.NotificationCompat;  
import android.support.v4.app.NotificationManagerCompat;  
import android.support.v4.app.NotificationCompat.WearableExtender;

 

### Create Notifications with the Notification Builder

The [v4 support
library](<http://developer.android.com/tools/support-library/features.html#v4>) allows
you to create notifications using the latest notification features such as
action buttons and large icons, while remaining compatible with Android 1.6 (API
level 4) and higher.

To create a notification with the support library, you create an instance
of [NotificationCompat.Builder](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html>) and
issue the notification by passing it
to [notify()](<https://developer.android.com/reference/java/lang/Object.html#notify()>).
For example:

int notificationId = 001;  
// Build intent for notification content  
Intent viewIntent = new Intent(this, ViewEventActivity.class);  
viewIntent.putExtra(EXTRA\_EVENT\_ID, eventId);  
PendingIntent viewPendingIntent =  
        PendingIntent.getActivity(this, 0, viewIntent, 0);  
  
NotificationCompat.Builder notificationBuilder =  
        new NotificationCompat.Builder(this)  
        .setSmallIcon(R.drawable.ic\_event)  
        .setContentTitle(eventTitle)  
        .setContentText(eventLocation)  
        .setContentIntent(viewPendingIntent);  
  
// Get an instance of the NotificationManager service  
NotificationManagerCompat notificationManager =  
        NotificationManagerCompat.from(this);  
  
// Build the notification and issues it with notification manager.  
notificationManager.notify(notificationId, notificationBuilder.build());

 

When this notification appears on a handheld device, the user can invoke
the [PendingIntent](<https://developer.android.com/reference/android/app/PendingIntent.html>) specified
by the
[setContentIntent()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#setContentIntent(android.app.PendingIntent)>) method
by touching the notification. When this notification appears on an Android
wearable, the user can swipe the notification to the left to reveal
the **Open** action, which invokes the intent on the handheld device.

 

### Add Action Buttons

In addition to the primary content action defined by
[setContentIntent()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#setContentIntent(android.app.PendingIntent)>),
you can add other actions by passing a
[PendingIntent](<https://developer.android.com/reference/android/app/PendingIntent.html>) to
the [addAction()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#addAction(android.support.v4.app.NotificationCompat.Action)>) method.

For example, the following code shows the same type of notification from above,
but adds an action to view the event location on a map.

// Build an intent for an action to view a map  
Intent mapIntent = new Intent(Intent.ACTION\_VIEW);  
Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));  
mapIntent.setData(geoUri);  
PendingIntent mapPendingIntent =  
        PendingIntent.getActivity(this, 0, mapIntent, 0);  
  
NotificationCompat.Builder notificationBuilder =  
        new NotificationCompat.Builder(this)  
        .setSmallIcon(R.drawable.ic\_event)  
        .setContentTitle(eventTitle)  
        .setContentText(eventLocation)  
        .setContentIntent(viewPendingIntent)  
        **.addAction(R.drawable.ic\_map,**  
**                getString(R.string.map), mapPendingIntent);**

On a handheld, the action appears as an additional button attached to the
notification. On a wearable, the action appears as a large button when the user
swipes the notification to the left. When the user taps the action, the
associated intent is invoked on the handheld.

 

### Specify Wearable-only Actions

If you want the actions available on the wearable to be different from those on
the handheld, then
use[WearableExtender.addAction()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.WearableExtender.html#addAction(android.support.v4.app.NotificationCompat.Action)>).
Once you add an action with this method, the wearable does not display any other
actions added
with [NotificationCompat.Builder.addAction()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#addAction(android.support.v4.app.NotificationCompat.Action)>).
That is, only the actions added
with[WearableExtender.addAction()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.WearableExtender.html#addAction(android.support.v4.app.NotificationCompat.Action)>) appear
on the wearable and they do not appear on the handheld.

// Create an intent for the reply action  
Intent actionIntent = new Intent(this, ActionActivity.class);  
PendingIntent actionPendingIntent =  
        PendingIntent.getActivity(this, 0, actionIntent,  
                PendingIntent.FLAG\_UPDATE\_CURRENT);  
  
// Create the action  
NotificationCompat.Action action =  
        new NotificationCompat.Action.Builder(R.drawable.ic\_action,  
                getString(R.string.label), actionPendingIntent)  
                .build();  
  
// Build the notification and add the action via WearableExtender  
Notification notification =  
        new NotificationCompat.Builder(mContext)  
                .setSmallIcon(R.drawable.ic\_message)  
                .setContentTitle(getString(R.string.title))  
                .setContentText(getString(R.string.content))  
                .extend(new WearableExtender().addAction(action))  
                .build();

 

### Add a Big View

You can insert extended text content to your notification by adding one of the
"big view" styles to your notification. On a handheld device, users can see the
big view content by expanding the notification. On a wearable device, the big
view content is visible by default.

To add the extended content to your notification,
call [setStyle()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#setStyle(android.support.v4.app.NotificationCompat.Style)>) on
the
[NotificationCompat.Builder](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html>) object,
passing it an instance of either
[BigTextStyle](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.BigTextStyle.html>) or [InboxStyle](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.InboxStyle.html>).

For example, the following code adds an instance of
[NotificationCompat.BigTextStyle](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.BigTextStyle.html>) to
the event notification, in order to include the complete event description
(which includes more text than can fit into the space provided for
[setContentText()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#setContentText(java.lang.CharSequence)>)).

// Specify the 'big view' content to display the long  
// event description that may not fit the normal content text.  
BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();  
bigStyle.bigText(eventDescription);  
  
NotificationCompat.Builder notificationBuilder =  
        new NotificationCompat.Builder(this)  
        .setSmallIcon(R.drawable.ic\_event)  
        .setLargeIcon(BitmapFactory.decodeResource(  
                getResources(), R.drawable.notif\_background))  
        .setContentTitle(eventTitle)  
        .setContentText(eventLocation)  
        .setContentIntent(viewPendingIntent)  
        .addAction(R.drawable.ic\_map,  
                getString(R.string.map), mapPendingIntent)  
        **.setStyle(bigStyle);**

Notice that you can add a large icon image to any notification using
the [setLargeIcon()](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#setLargeIcon(android.graphics.Bitmap)>) method.
However, these icons appear as large background images on a wearable and do not
look good as they are scaled up to fit the wearable screen. To add a
wearable-specific background image to a notification, see [Add Wearable Features
For a
Notification](<https://developer.android.com/training/wearables/notifications/creating.html#AddWearableFeatures>).
For more information about designing notifications with large images, see
the [Design Principles of Android
Wear](<https://developer.android.com/design/wear/index.html>).

 

### Deliver the Notification

When you want to deliver your notifications, always use
the [NotificationManagerCompat](<https://developer.android.com/reference/android/support/v4/app/NotificationManagerCompat.html>) API
instead of
[NotificationManager](<https://developer.android.com/reference/android/app/NotificationManager.html>):

// Get an instance of the NotificationManager service  
NotificationManagerCompat notificationManager =  
        NotificationManagerCompat.from(mContext);  
  
// Issue the notification with notification manager.  
notificationManager.notify(notificationId, notif);

If you use the
framework's [NotificationManager](<https://developer.android.com/reference/android/app/NotificationManager.html>),
some features from
[NotificationCompat.WearableExtender](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.WearableExtender.html>) do
not work, so make sure to
use [NotificationCompat](<https://developer.android.com/reference/android/support/v4/app/NotificationCompat.html>).

 

 

 

[[1]](<#_ftnref1 https://developer.android.com/design/wear/principles.html>)
<https://developer.android.com/design/wear/principles.html>

[[2]](<#_ftnref2
https://developer.android.com/training/wearables/notifications/creating.html>)
<https://developer.android.com/training/wearables/notifications/creating.html>
