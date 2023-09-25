/**
 * 
 */
const showValue = (target) => {
  if (target.selectedIndex == 1 || target.selectedIndex == 2) {
    document.getElementById('box').innerHTML = '<input type="text" size="15" name="searchtext">';
  } else if (target.selectedIndex == 3 || target.selectedIndex == 4) {
    document.getElementById('box').innerHTML = '<input type="hidden" value="" name="searchtext">';
  } 
}