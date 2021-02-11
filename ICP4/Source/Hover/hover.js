function upDate(previewPic) {
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image
       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
      // extracted info of the element  like image url and alternate text using id attribute and then set the 
      // new image using url css function and text using innerText property
      const imageid = document.getElementById("image");
      const imageurl= previewPic.src;
      const imagetxt= previewPic.alt;

      imageid.style.backgroundImage = "url("+imageurl+")";
      imageid.innerText=imagetxt;  
}
function unDo() {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was
   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */
   //extracting the info of element and setting new properties like empty background image and older text
   const imageid = document.getElementById("image");
   imageid.style.backgroundImage="url('_')"; 
   imageid.innerText="Hover over an image below to display here";

}
