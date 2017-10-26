/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.controls.scrolling;

import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.Element;
import tonegod.gui.core.ElementManager;
import tonegod.gui.core.utils.UIDUtil;
import tonegod.gui.effects.Effect;

/**
 *
 * @author t0neg0d
 */
public abstract class CustomScrollBar extends Element
{
   ButtonAdapter btnScrollTrack, btnScrollUp, btnScrollDown, btnScrollThumb;
   int btnInc = 1, trackInc = 10;
   MouseButtonEvent trackEvent = null;

   /**
    * Creates a new instance of a Vertical Screll Bar
    * @param screen The screen the element will be added to
    * @param position The initial position of the scrollbar
    * @param dimensions The initial dimentions of the scrollbar
    */
   public CustomScrollBar(ElementManager screen, Vector2f position, Vector2f dimensions)
   {
      this(screen, UIDUtil.getUID(), position, dimensions);
   }

   /**
    * Creates a new instance of a Vertical Screll Bar
    * @param screen The screen the element will be added to
    * @param UID A Unique String ID for the Scrollbar
    * @param position The initial position of the scrollbar
    * @param dimensions The initial dimentions of the scrollbar
    */
   public CustomScrollBar(ElementManager screen, String UID, Vector2f position, Vector2f dimensions)
   {
      super(screen, UID, position, dimensions, new Vector4f(0, 0, 0, 0), null);

      this.setScaleNS(true);
      this.setScaleEW(false);
      this.setDocking(Docking.NE);
      this.setAsContainerOnly();

      btnScrollTrack = new ButtonAdapter(screen, UID + "btnScrollTrack", new Vector2f(0, getWidth()), new Vector2f(getWidth(), getHeight() - (getWidth() * 2)),
                                         screen.getStyle("ScrollArea#VScrollBar").getVector4f("trackResizeBorders"),
                                         screen.getStyle("ScrollArea#VScrollBar").getString("trackImg"))
      {
         @Override
         public void onButtonMouseLeftDown(MouseButtonEvent evt, boolean toggled)
         {
            trackEvent = evt;
            if (trackEvent.getY() - getAbsoluteY() < btnScrollThumb.getY())
            {
               if (btnScrollThumb.getY() - trackInc > 0)
               {
                  btnScrollThumb.setY(btnScrollThumb.getY() - trackInc);
               }
               else
               {
                  btnScrollThumb.setY(0);
               }
               stateChanged(true);
            }
            else if (trackEvent.getY() - getAbsoluteY() > btnScrollThumb.getY() + btnScrollThumb.getHeight())
            {
               if (btnScrollThumb.getY() + trackInc < btnScrollTrack.getHeight() - btnScrollThumb.getHeight())
               {
                  btnScrollThumb.setY(btnScrollThumb.getY() + trackInc);
               }
               else
               {
                  btnScrollThumb.setY(btnScrollTrack.getHeight() - btnScrollThumb.getHeight());
               }
               stateChanged(true);
            }
         }

         @Override
         public void onButtonMouseRightDown(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled)
         {
            stateChanged(false);
         }

         @Override
         public void onButtonMouseRightUp(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonStillPressedInterval()
         {
            if (trackEvent.getY() - getAbsoluteY() < btnScrollThumb.getY())
            {
               if (btnScrollThumb.getY() - trackInc > 0)
               {
                  btnScrollThumb.setY(btnScrollThumb.getY() - trackInc);
               }
               else
               {
                  btnScrollThumb.setY(0);
               }
               stateChanged(true);
            }
            else if (trackEvent.getY() - getAbsoluteY() > btnScrollThumb.getY() + btnScrollThumb.getHeight())
            {
               if (btnScrollThumb.getY() + trackInc < btnScrollTrack.getHeight() - btnScrollThumb.getHeight())
               {
                  btnScrollThumb.setY(btnScrollThumb.getY() + trackInc);
               }
               else
               {
                  btnScrollThumb.setY(btnScrollTrack.getHeight() - btnScrollThumb.getHeight());
               }
               stateChanged(true);
            }
         }

         @Override
         public void onButtonFocus(MouseMotionEvent evt)
         {
         }

         @Override
         public void onButtonLostFocus(MouseMotionEvent evt)
         {
         }
      };
      btnScrollTrack.setScaleEW(false);
      btnScrollTrack.setScaleNS(true);
      btnScrollTrack.setDocking(Docking.SW);
      btnScrollTrack.setInterval(100);
      btnScrollTrack.setTileImageByKey("ScrollArea#VScrollBar", "tileTrackImg");
      this.addChild(btnScrollTrack);

      btnScrollTrack.removeEffect(Effect.EffectEvent.Hover);
      btnScrollTrack.removeEffect(Effect.EffectEvent.Press);
      btnScrollTrack.removeEffect(Effect.EffectEvent.Release);

      btnScrollThumb = new ButtonAdapter(screen, UID + "btnScrollThumb", new Vector2f(0, 0), new Vector2f(getWidth(), getWidth()),
                                         screen.getStyle("ScrollArea#VScrollBar").getVector4f("thumbResizeBorders"),
                                         screen.getStyle("ScrollArea#VScrollBar").getString("thumbImg"))
      {
         @Override
         public void onButtonMouseLeftDown(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonMouseRightDown(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled)
         {
            stateChanged(false);

         }

         @Override
         public void onButtonMouseRightUp(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonStillPressedInterval()
         {

         }

         @Override
         public void controlMoveHook()
         {
            stateChanged(true);
         }

         @Override
         public void onButtonFocus(MouseMotionEvent evt)
         {
         }

         @Override
         public void onButtonLostFocus(MouseMotionEvent evt)
         {
         }
      };
      btnScrollThumb.setButtonHoverInfo(screen.getStyle("ScrollArea#VScrollBar").getString("thumbHoverImg"), ColorRGBA.White);
      btnScrollThumb.setButtonPressedInfo(screen.getStyle("ScrollArea#VScrollBar").getString("thumbPressedImg"), ColorRGBA.Gray);
      btnScrollThumb.setIsMovable(true);
      btnScrollThumb.setlockToParentBounds(true);
      btnScrollThumb.setScaleEW(false);
      btnScrollThumb.setScaleNS(true);
      btnScrollThumb.setDocking(Docking.NW);
      btnScrollTrack.addChild(btnScrollThumb);

      btnScrollUp = new ButtonAdapter(screen, UID + "btnScrollUp", new Vector2f(0, 0), new Vector2f(getWidth(), getWidth()),
                                      screen.getStyle("ScrollArea#VScrollBar").getVector4f("btnUpResizeBorders"),
                                      screen.getStyle("ScrollArea#VScrollBar").getString("btnUpImg"))
      {
         @Override
         public void onButtonMouseLeftDown(MouseButtonEvent evt, boolean toggled)
         {
            if (btnScrollThumb.getY() < (btnScrollTrack.getHeight() - btnScrollThumb.getHeight()))
            {
               btnScrollThumb.setY(btnScrollThumb.getY() + btnInc);
            }
            stateChanged(true);
         }

         @Override
         public void onButtonMouseRightDown(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled)
         {

            stateChanged(false);
         }

         @Override
         public void onButtonMouseRightUp(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonStillPressedInterval()
         {
            if (btnScrollThumb.getY() < (btnScrollTrack.getHeight() - btnScrollThumb.getHeight()))
            {
               btnScrollThumb.setY(btnScrollThumb.getY() + btnInc);
            }
            stateChanged(true);
         }

         @Override
         public void onButtonFocus(MouseMotionEvent evt)
         {
         }

         @Override
         public void onButtonLostFocus(MouseMotionEvent evt)
         {
         }
      };
      btnScrollUp.setButtonHoverInfo(screen.getStyle("ScrollArea#VScrollBar").getString("btnUpHoverImg"), ColorRGBA.White);
      btnScrollUp.setButtonPressedInfo(screen.getStyle("ScrollArea#VScrollBar").getString("btnUpPressedImg"), ColorRGBA.Gray);
      btnScrollUp.setScaleEW(false);
      btnScrollUp.setScaleNS(false);
      btnScrollUp.setDocking(Docking.NW);
      btnScrollUp.setInterval(100);
      if (screen.getStyle("ScrollArea#VScrollBar").getBoolean("useBtnUpArrowIcon"))
      {
         btnScrollUp.setButtonIcon(18, 18, screen.getStyle("Common").getString("arrowUp"));
      }
      this.addChild(btnScrollUp);

      btnScrollDown = new ButtonAdapter(screen, UID + "btnScrollDown", new Vector2f(0, getHeight() - getWidth()), new Vector2f(getWidth(), getWidth()),
                                        screen.getStyle("ScrollArea#VScrollBar").getVector4f("btnDownResizeBorders"),
                                        screen.getStyle("ScrollArea#VScrollBar").getString("btnDownImg"))
      {
         @Override
         public void onButtonMouseLeftDown(MouseButtonEvent evt, boolean toggled)
         {
            if (btnScrollThumb.getY() > 0)
            {
               btnScrollThumb.setY(btnScrollThumb.getY() - btnInc);
            }
            stateChanged(true);
         }

         @Override
         public void onButtonMouseRightDown(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled)
         {
            stateChanged(false);

         }

         @Override
         public void onButtonMouseRightUp(MouseButtonEvent evt, boolean toggled)
         {
         }

         @Override
         public void onButtonStillPressedInterval()
         {
            if (btnScrollThumb.getY() > 0)
            {
               btnScrollThumb.setY(btnScrollThumb.getY() - btnInc);
            }
            stateChanged(true);
         }

         @Override
         public void onButtonFocus(MouseMotionEvent evt)
         {
         }

         @Override
         public void onButtonLostFocus(MouseMotionEvent evt)
         {
         }
      };
      btnScrollDown.setButtonHoverInfo(screen.getStyle("ScrollArea#VScrollBar").getString("btnDownHoverImg"), ColorRGBA.White);
      btnScrollDown.setButtonPressedInfo(screen.getStyle("ScrollArea#VScrollBar").getString("btnDownPressedImg"), ColorRGBA.Gray);
      btnScrollDown.setScaleEW(false);
      btnScrollDown.setScaleNS(false);
      btnScrollDown.setDocking(Docking.SW);
      btnScrollDown.setInterval(100);
      if (screen.getStyle("ScrollArea#VScrollBar").getBoolean("useBtnDownArrowIcon"))
      {
         btnScrollDown.setButtonIcon(18, 18, screen.getStyle("Common").getString("arrowDown"));
      }
      this.addChild(btnScrollDown);
   }

   public ButtonAdapter getScrollTrack()
   {
      return this.btnScrollTrack;
   }

   public ButtonAdapter getScrollThumb()
   {
      return this.btnScrollThumb;
   }

   public ButtonAdapter getScrollButtonUp()
   {
      return this.btnScrollUp;
   }

   public ButtonAdapter getScrollButtonDown()
   {
      return this.btnScrollDown;
   }

   /**
    * Sets the increment value used when scrolling by arrow button click
    * @param btnInc int
    */
   public void setButtonInc(int btnInc)
   {
      this.btnInc = btnInc;
   }

   /**
    * Returns the increment value used when scrolling by arrow button click
    * @return int
    */
   public int getButtonInc()
   {
      return this.btnInc;
   }

   /**
    * Sets the increment value used when scrolling by track click
    * @param trackInc int
    */
   public void setTrackInc(int trackInc)
   {
      this.trackInc = trackInc;
   }

   /**
    * Returns the increment used when scrolling by track click
    * @return int
    */
   public int getTrackInc()
   {
      return this.trackInc;
   }

   /**
    * Internal use only.  See ScrollArea
    */
   protected void scrollToTop()
   {
      btnScrollThumb.setY(btnScrollTrack.getHeight() - btnScrollThumb.getHeight());
   }

   /**
    * Internal use only.  See ScrollArea
    */
   protected void scrollToBottom()
   {
      btnScrollThumb.setY(0);
   }

   private int getCurrentIndex()
   {
      btnInc = (int) ((1 / (btnScrollTrack.getDimensions().y - btnScrollThumb.getDimensions().y)) * 1000.0f);
      trackInc = btnInc * 10;
      int index = (int) ((btnScrollThumb.getY() / (btnScrollTrack.getDimensions().y - btnScrollThumb.getDimensions().y)) * 100.0f);
      if (index < 0)
         index = 0;
      if (index > 100)
      {
         index = 100;
      }
      return index;

   }

   private void stateChanged(boolean pressed)
   {

      indexChanged(getCurrentIndex(), pressed);

   }

   public abstract void indexChanged(int index, boolean buttonPressed);

}
