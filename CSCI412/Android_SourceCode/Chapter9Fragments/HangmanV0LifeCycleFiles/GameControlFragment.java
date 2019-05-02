package com.jblearning.hangmanv0lifecycle;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameControlFragment extends Fragment {
  public static String MA = "MainActivity";

  public GameControlFragment( ) {
    Log.w( MA, "Inside GameControlFragment:constructor" );
  }

  public void onAttach ( Context context ) {
    super.onAttach( context );
    Log.w( MA, "Inside GameControlFragment:onAttach" );
  }

  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    Log.w( MA, "Inside GameControlFragment:onCreate" );
  }

  @Override
  public View onCreateView( LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState ) {
    Log.w( MA, "Inside GameControlFragment:onCreateView" );
    return inflater.inflate( R.layout.fragment_game_control,
      container, false );
  }

  public void onViewCreated (View view, Bundle savedInstanceState) {
    super.onViewCreated( view, savedInstanceState );
    Log.w( MA, "Inside GameControlFragment:onViewCreated" );
  }

  public void onActivityCreated( Bundle savedInstanceState ) {
    super.onActivityCreated( savedInstanceState );
    Log.w( MA, "Inside GameControlFragment:onActivityCreated" );
  }

  public void onViewStateRestored( Bundle savedInstanceState ) {
    super.onViewStateRestored( savedInstanceState );
    Log.w( MA, "Inside GameControlFragment:onViewStateRestored" );
  }

  public void onStart( ) {
    super.onStart();
    Log.w( MA, "Inside GameControlFragment:onStart" );
  }

  public void onResume( ) {
    super.onResume();
    Log.w( MA, "Inside GameControlFragment:onResume" );
  }

  public void onPause( ) {
    super.onPause();
    Log.w( MA, "Inside GameControlFragment:onPause" );
  }

  public void onStop( ) {
    super.onStop();
    Log.w( MA, "Inside GameControlFragment:onStop" );
  }

  public void onDestroyView () {
    super.onDestroyView();
    Log.w( MA, "Inside GameControlFragment:onDestroyView" );
  }

  public void onDestroy( ) {
    super.onDestroy();
    Log.w( MA, "Inside GameControlFragment:onDestroy" );
  }

  public void onDetach( ) {
    super.onDetach();
    Log.w( MA, "Inside GameControlFragment:onDetach" );
  }
}