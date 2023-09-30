import icons from './../../assets/sprite.svg';
import ReactDOM from 'react-dom';
const Spinner = () => {
    const overlayDiv = document.getElementById('modal');
    return (
        <>  {
            ReactDOM.createPortal(<div className="spinner">
                <svg className='icon icon-loader'>
                    <use href={`${icons}#icon-loader`}></use>
                </svg>
            </div >, overlayDiv)
        }</>
    )
}

export default Spinner;