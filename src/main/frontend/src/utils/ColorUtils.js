export const getFontColorBasedOnBrightness = (color='#ffffff') => {
    if(!color){
        color = '#ffffff';
    }
    const r = parseInt(color.substr(1, 2), 16);
    const g = parseInt(color.substr(3, 2), 16);
    const b = parseInt(color.substr(5, 2), 16);
    let brightness=  (r * 0.299 + g * 0.587 + b * 0.114) / 255;
    return brightness > 0.7 ? '#000000' : '#ffffff'
  };
  

  export function getRandomBlueShade() {
    // Generate random values for red and green components
    const red = Math.floor(Math.random() * 256); // Random value between 0 and 255
    const green = Math.floor(Math.random() * 256); // Random value between 0 and 255
    
    
    const blue =  Math.floor(Math.random() * 256);; 
    
    // Convert the RGB components to hexadecimal format
    const redHex = red.toString(16).padStart(2, '0');
    const greenHex = green.toString(16).padStart(2, '0');
    const blueHex = blue.toString(16).padStart(2, '0');
    
    // Combine the components to create the hex color code
    const hexColor = `#42${greenHex}f5`;
    console.log(hexColor);
    return hexColor;
  }
