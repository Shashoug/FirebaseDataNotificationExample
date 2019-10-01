public class FirebaseClientService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    Utilities utils = new Utilities();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() != null) {
            Log.e("DATA", remoteMessage.getData().toString());
            try {
                Map<String, String> params = remoteMessage.getData();
                JSONObject object = new JSONObject(params);
                Log.e("JSON OBJECT", object.toString());
                String name = object.getString("name");
                String source = object.getString("source");
                String destination = object.getString("destination");
                String distance = object.getString("distance");
                String price = object.getString("price");
                String trip_id = object.getString("trip_id");
                Intent parsedIntent = new Intent(getApplicationContext(), IncomingTripActivity.class);
                parsedIntent.putExtra("name", name);
                parsedIntent.putExtra("source", source);
                parsedIntent.putExtra("destination", destination);
                parsedIntent.putExtra("distance", distance);
                parsedIntent.putExtra("price", price);
                parsedIntent.putExtra("trip_id",trip_id);
                parsedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                parsedIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parsedIntent);


                //rest of the code
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
