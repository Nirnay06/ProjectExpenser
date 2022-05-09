import { Input } from "antd";
import { SearchOutlined } from '@ant-design/icons'
const SearchField = (props) => {
    const { searchValue, setSearchValue, className, style } = props;
    return (
        <div className={`searchField ${className ? className : ''}`} style={style}>
            <SearchOutlined style={{ 'fontSize': '2rem', 'margin': 'auto' }} />
            <Input placeholder="Search" value={searchValue} onChange={setSearchValue}></Input>
        </div>
    )
}


export default SearchField;