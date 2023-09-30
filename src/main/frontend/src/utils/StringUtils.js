export const getFieldName = (name) => {
    const arr = name.replaceAll("]", "").replaceAll("[", ".").split(".");
    return arr;
}

export const getCurrencyFormatted= (amount , parameters ={})=>{
    if(!amount){
        amount =0;
    }
    return  amount.toLocaleString( 'en-us',  {
        style: 'currency',
        currency: 'INR',
        maximumFractionDigits: 2,
        minimumFractionDigits: 0,
        ...parameters
     } )
}


export const getNumberShorthand= (amount , parameters ={})=>{
    if(!amount){
        amount =0;
    }
    return  amount.toLocaleString( 'en-us',  {
        notation : 'compact',
        ...parameters
     } )
}