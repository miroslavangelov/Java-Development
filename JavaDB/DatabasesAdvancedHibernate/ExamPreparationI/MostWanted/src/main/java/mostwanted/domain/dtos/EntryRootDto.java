package mostwanted.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryRootDto {
    @XmlElement(name = "entry")
    private EntryDto[] entryDtos;

    public EntryRootDto() {
    }

    public EntryDto[] getEntryDtos() {
        return entryDtos;
    }

    public void setEntryDtos(EntryDto[] entryDtos) {
        this.entryDtos = entryDtos;
    }
}
