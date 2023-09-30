import { Input } from "antd";
import { SearchOutlined } from '@ant-design/icons'
const SearchField = (props) => {
    const { setSearchValue, className, style } = props;
    return (
        <div className={`searchField ${className ? className : ''}`} style={style}>
            <SearchOutlined style={{ 'fontSize': '2rem', 'margin': 'auto' }} />
            <Input placeholder="Search" onChange={(e)=>{setSearchValue(e.target.value)}}></Input>
        </div>
    )
}


export default SearchField;