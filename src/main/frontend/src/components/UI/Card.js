const Card = ({ className, children, ...props }) => {
    return (
        <div className={`Card ${className}`} {...props} >
            {children}
        </div>
    );
}

export default Card;