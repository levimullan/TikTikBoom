# TickTickBoom

## A high-stress photo sharing game

**TickTickBoom** is a game in which users will use strategic decision-making
to minimize public humiliation.

Each user will link import their mobile photos from a gallery into the app. At this point they will be able to view a
grid of all photos in the "PHOTOS" tab. Then, a user will go to the "GAME" display, where they will be presented with
15 randomly selected photos from their Gallery. From these 15 photos, they will have to select one that they will publish
to a public feed of photos.

Hopefully you don't have too many embarrassing photos in your album!

Let the games begin. :)

![Alt Text](https://notesfromachair.files.wordpress.com/2022/07/mean-girls-regina-george.gif)


**User Stories (Phase 1)**

- I want to be able to add an Image (X) to my Gallery (Y) by clicking the "+" button and navigating my computer's
  directory for an Image (X).
- I want to be able to view all the Images (X) in my Gallery (Y) by clicking the "PHOTOS" button.
- I want to be able to retrieve a 15-item SubGallery (Z) of Images (X) that are randomly selected from my Gallery (Y)
  which is accessed via the "GAME" button.
- I want to be able to select an Image (X) from the 15-item SubGallery (Y) that will be published to a Public Gallery (P).
- I want to be able to view all the Images (X) in the SubGallery (P) by clicking the "FEED" button.

**User Stories (Phase 2)**
- I want to have the OPTION be able to save the entire state of the application Gallery (x) and the Feed (P) to file
- I want to have the OPTION to reload the entire state of the application, that is my Gallery (x) and the Feed (P), from
  file when starting the application


# Instructions for Grader

**NOTE: Several commits were made after the deadline to clean-up/add class level comments and proper SPECS that were forgotten in the UI class. No worries if you cant include these additions when you are doing your grading, I am willing to accept the 10% deduction for missing specifications and class level comments. There were no implementation changes made after the deadline and I wont be mad if you choose to mark the version submitted prior to the deadline instead. Figured I'd try! Cheers man!**

**1) Instructions for adding an Image (X) to a Y (List of Images):**

- FIRST REQUIRED ACTION: You can generate the first required action related to the user story "adding multiple Xs (photos) to a Y (gallery
  of photos) by click the "+" button in the bottom left hand corner of the GUI. A navigation window will pop up in 
  which a user will be able to copy that file to their user application directory and is able to add the new path location 
  to their list of filePaths on their user profile. 
- SECOND REQUIRED ACTION: You can generate the second required action related to the user story "seeing all X's (photos) that have been added
  to Y (gallery) by clicking on the "PHOTO" button. This will update the gallery to include the photos that were added in 
  the step above.
- FIRST RELATED ACTION: You can see a subset of 15-randomly chosen photos from the overall gallery if you click on the
  "GAME" panel. These are the photos from which you will choose a photo to publish to the public feed.
- SECOND RELATED ACTION: You can select a photo from the 15-randomly chosen photos to publish to the public feed by clicking
  on one of the photos. A green border should appear. Next, click on the "FEED" button to see the photo you just published.
- VISUAL COMPONENT: You can locate my visual component at the following locations:
  - a) The Photo Page: which displays all unpublished photos from a users gallery in a 3-column scrollable grid.
  - b) The Game Page: which displays a subset of 15 unpublished photos from a users gallery in a 3-column grid. Note:
       You may have to jiggle your scroll wheel over the frame to load these image.
  - c) The Feed Page: which displays a list of all published photos from all the users of this workspace in a single
     column grid.

- You can **save** the state of the application by click the close button in the top left corner of the application frame. A dialog window should
  pop up that asks you if you'd like to save the workspace. Click "YES" if you'd like to save and "NO" if not.
- You can **reload** previously saved application state when you start the application. When you run the application you will be prompted via
  a popup dialog window to see if you would like to load your profile. Click "YES" if you would like to previously saved state for which you are an
  existing user. You will then be asked to enter your existing username. Click "NO" if you would like to create a new profile.

**Phase 4: Task 2**

**NOTE ON THE UML:** Anything outside of the three main colour blocks is a built-in java swing class. I included them
for my own understanding to see what classes I extended when creating my own custom classes.

Example Log:

Tue Apr 02 15:27:13 PDT 2024

**User loaded state from data/users.json**

Tue Apr 02 15:27:13 PDT 2024

**User directory set to: /Users/levimullan/Desktop/project_j1g5f/./data/photos1**

Tue Apr 02 15:27:13 PDT 2024

**File added to: /Users/levimullan/Desktop/project_j1g5f/./data/photos1**

Tue Apr 02 15:27:54 PDT 2024

**User photo published: /Users/levimullan/Desktop/project_j1g5f/./data/photos1/1ADE4EC5-75F2-4C20-8143-F60F2AA844AD.jpeg**

Tue Apr 02 15:28:02 PDT 2024

**Application state saved state to: data/users.json**


**Phase 4: Task 3**

1. Currently, each _PhotoLabel_ (for which there are hundreds of instances) instantiates its own _MouseListener_ and 
_Dimension_ classes. Since the _MouseListener_ and _Dimensions_ are the same for ALL _PhotoLabel_ instances, I would use the
Singleton Pattern (using a static field in the _PhotoLabel_ class) so that ALL PhotoLabels can have access to ONE instance of both
_Dimension_ and _MouseListener_. 

2. Presently, the software is designed to create and load labels into the AppPanel user interface before the program frame 
even finishes initializing. Per conversations with the TA supervising this project, I would explore ways in which this
data is loaded on an "as-needed" basis as opposed to all at once at the app initialization (which is slow). One solution might be to add
an Action or EventListener to the JScrollPane so that only when the top row of photos leaves the top of the panel window
does the next set of photos load at the bottom. 

2. When looking at the the UML, there appears to be some coupling. For example, as it is currently designed,
three UI classes--_App_, _AppFrame_, and _AppPanel_--depend on both the _User_ and _Users_ classes in the model package.
The AppFrame currently depends on the _User_ and _Users_ class for the sole purpose of passing instantiations of those
classes down to the AppPanel which retrieves and displays photo data stored in these two classes.  This seems to be an 
unnecessary dependency since it its only purpose is to relay _User_ and _Users_ instantiations to
components added to the _AppFrame_. I would thus remove dependencies from the _AppFrame_ and have _AppPanel_ access _User_ and
_Users_ from the App class directly. 


