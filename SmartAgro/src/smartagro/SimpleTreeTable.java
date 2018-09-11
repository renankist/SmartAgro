package smartagro;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

/**
 * A simple JXTreeTable application to demonstrate and explain  
 * basic functionality.
 */
public class SimpleTreeTable extends JFrame
{
  private Object[][][] data = new Object[][][]
  {
    {
      {"1. Violin"},
      {"Guy", "Braunstein", true, "Violin", "1. Konzertmeister", 1971},
      {"Daniel", "Stabrawa", true, "Violin", "1. Konzertmeister", 1955},
      {"Toru", "Yasunaga", true, "Violin", "1. Konzertmeister", 1951},
      {"Rainer", "Sonne", true, "Violin", "Konzertmeister", 1950},
      {"Zoltán", "Almási", true, "Violin", "", 1964},
      {"Maja", "Avramovic", false, "Violin", "", 1967},
      {"Simon", "Bernardini", true, "Violin", "", 1975},
      {"Wolfram", "Brandl", true, "Violin", "", 1975},
      {"Peter", "Brem", true, "Violin", "", 1951},
      {"Armin", "Brunner", true, "Violin", "", 1945},
      {"Andreas", "Buschatz", true, "Violin", "", 1975},
      {"Alessandro", "Cappone", true, "Violin", "", 1957},
      {"Madeleine", "Carruzzo", false, "Violin", "", -1},
      {"Aline", "Champion", false, "Violin", "", 1971},
      {"Laurentius", "Dinca", true, "Violin", "", 1953},
      {"Sebastian", "Heesch", true, "Violin", "", 1964},
      {"Felicitas", "Hofmeister", false, "Violin", "", 1972},
      {"Aleksandar", "Ivic", true, "Violin", "", 1962},
      {"Rüdiger", "Liebermann", true, "Violin", "", 1956},
      {"Kotowa", "Machida", false, "Violin", "", 1966},
      {"Helmut", "Mebert", true, "Violin", "", 1943},
      {"Andreas", "Neufeld", true, "Violin", "", 1976},
      {"Bastian", "Schäfer", true, "Violin", "", 1960},
    }, 
    {
      {"2. Violin"},
      {"Christian", "Stadelmann", true, "Violin", "1. Stimmführer", 1959},
      {"Thomas", "Timm", true, "Violin", "1. Stimmführer", 1973},
      {"Axel", "Gerhardt", true, "Violin", "Stimmführer", 1943},
      {"Holm", "Birkholz", true, "Violin", "", 1952},
      {"Stanley", "Dodds", true, "Violin", "", 1970},
      {"Cornelia", "Gartemann", false, "Violin", "", 1977},
      {"Amadeus", "Heutling", true, "Violin", "", 1956},
      {"Christophe", "Horak", true, "Violin", "", 1977},
      {"Rainer", "Mehne", true, "Violin", "", 1947},
      {"Christoph", "von der Nahmer", true, "Violin", "", 1975},
      {"Raimar", "Orlovsky", true, "Violin", "", 1966},
      {"Heinz-Henning", "Perschel", true, "Violin", "", 1941},
      {"Bettina", "Sartorius", false, "Violin", "", 1970},
      {"Rachel", "Schmidt", false, "Violin", "", 1975},
      {"Armin", "Schubert", true, "Violin", "", 1967},
      {"Stephan", "Schulze", true, "Violin", "", 1956},
      {"Christoph", "Streuli", true, "Violin", "", 1964},
      {"Eva-Maria", "Tomasi", false, "Violin", "", 1962},
      {"Romano", "Tommasini", true, "Violin", "", 1960},
    },     
    {
      {"Violas"},
      {"Carrie", "Dennis", false, "Viola", "1. Solo-Bratscherin", 1977}, 
      {"Neithard", "Resa", true, "Viola", "1. Solo-Bratscher", 1950},
      {"Naoko", "Shimizu", false, "Viola", "Solo-Bratscherin", 1968},
      {"Wilfried", "Strehle", true, "Viola", "Solo-Bratsche", 1947},
      {"Micha", "Afkham", true, "Viola", "", 1979},
      {"Julia", "Gartemann", false, "Viola", "", 1975},
      {"Matthew", "Hunter", true, "Viola", "", 1959},
      {"Ulrich", "Knörzer", true, "Viola", "", 1961},
      {"Sebastian", "Krunnies", true, "Viola", "", 1974},
      {"Walter", "Küssner", true, "Viola", "", 1962},
      {"Martin", "von der Nahmer", true, "Viola", "", 1978},
      {"Zdzislaw", "Polonek", true, "Viola", "", 1946},
      {"Martin", "Stegner", true, "Viola", "", 1967},
      {"Wolfgang", "Talirz", true, "Viola", "", 1960},
    },     
    {
      {"Violoncelli"},
      {"Georg", "Faust", true, "Violoncello", "1. Solo-Cellist", 1956},
      {"Ludwig", "Quandt", true, "Violoncello", "1. Solo-Cellist", 1961},
      {"Martin", "Löhr", true, "Violoncello", "Solo-Cellist", 1967},
      {"Olaf", "Maninger", true, "Violoncello", "Solo-Cellist", 1964},
      {"Jan", "Diesselhorst", true, "Violoncello", "", 1954},
      {"Richard", "Duven", true, "Violoncello", "", 1958},
      {"Christoph", "Igelbrink", true, "Violoncello", "", 1958},
      {"Solène", "Kermarrec", false, "Violoncello", "", 1983},
      {"Martin", "Menking", true, "Violoncello", "", 1967},
      {"David", "Riniker", true, "Violoncello", "", 1970},
      {"Nikolaus", "Römisch", true, "Violoncello", "", 1972},
      {"Dietmar", "Schwalke", true, "Violoncello", "", 1958},
      {"Knut", "Weber", true, "Violoncello", "", 1974},
    },     
    {
      {"Contrabass"},
      {"Nabil", "Shehata", true, "Contrabass", "1. Solo-Bassist", 1980},
      {"Klaus", "Stoll", true, "Contrabass", "1. Solo-Bassist", 1943},
      {"Rudolf", "Watzel", true, "Contrabass", "Solo-Bassist", 1943},
      {"Martin", "Heinze", true, "Contrabass", "", 1965},
      {"Wolfgang", "Kohly", true, "Contrabass", "", 1944},
      {"Esko", "Laine", true, "Contrabass", "", 1961},
      {"Peter", "Riegelbauer", true, "Contrabass", "", 1956},
      {"Edicson", "Ruiz", true, "Contrabass", "", 1985},
      {"Janne", "Saksala", true, "Contrabass", "", 1967},
      {"Janusz", "Widzyk", true, "Contrabass", "", 1973},
      {"Ulrich", "Wolff", true, "Contrabass", "", 1955},
    },     
    {
      {"Flutes"},
      {"Andreas", "Blau", true, "Flute", "Solo", 1949},
      {"Emmanuel", "Pahud", true, "Flute", "Solo", 1970},
      {"Michael", "Hasel", true, "Flute", "", 1959},
      {"Jelka", "Weber", false, "Flute", "", 1971},
    },     
    {
      {"Oboes"},
      {"Jonathan", "Kelly", true, "Oboe", "Solo", 1969},
      {"Albrecht", "Mayer", true, "Oboe", "Solo", 1965},
      {"Christoph", "Hartmann", true, "Oboe", "", 1965},
      {"Andreas", "Wittmann", true, "Oboe", "", 1961},
      {"Dominik", "Wollenweber", true, "Englischhorn", "", 1967},
    },     
    {
      {"Clarinets"},
      {"Wenzel", "Fuchs", true, "Clarinet", "Solo", 1963},
      {"Karl-Heinz", "Steffens", true, "Clarinet", "Solo", 1961},
      {"Alexander", "Bader", true, "Clarinet", "", 1965},
      {"Walte", "Seyfarth", true, "Clarinet", "", 1953},
      {"Manfred", "Preis", true, "Bassclarinet", "", 1954},
    },     
    {
      {"Bassoons"},
      {"Daniele", "Damiano", true, "Bassoon", "Solo", 1961},           
      {"Stefan", "Schweigert", true, "Bassoon", "Solo", 1962},
      {"Henning", "Trog", true, "Bassoon", "", 1940},
      {"Markus", "Weidmann", true, "Bassoon", "", 1968},
      {"Marion", "Reinhard", false, "Kontrafagott", "", 1972},
    },     
    {
      {"Horns"},
      {"Radek", "Baborak", true, "Horn", "Solo", 1976},           
      {"Stefan", "Dohr Solo", true, "Horn", "Solo", 1965},
      {"Norbert", "Hauptmann", true, "Horn", "", 1942},
      {"Stefan", "de Leval Jezierski", true, "hohes Horn", "", 1954},
      {"Fergus", "McWilliam", true, "Horn", "", 1952},
      {"Georg", "Schreckenberger", true, "Horn", "", 1970},
      {"Klaus", "Wallendorf", true, "Horn", "", 1948},
      {"Sarah", "Willis", false, "Horn", "", -1},
    },     
    {
      {"Trumpets"},
      {"Gábor", "Tarkövi", true, "Trumpet", "Solo", 1969},           
      {"Tamás", "Velenczei", true, "Trumpet", "Solo", 1962},
      {"Thomas", "Clamor", true, "Trumpet", "", 1963},
      {"Georg", "Hilser", true, "Trumpet", "", 1947},
      {"Martin", "Kretzer", true, "Trumpet", "", 1950},
    },     
    {
      {"Trombones"},
      {"Christhard", "Gössling", true, "Trombone", "Solo", 1957},
      {"Olaf", "Ott", true, "Trombone", "Solo", 1963},
      {"Thomas", "Leyendecker", true, "Trombone", "", 1980},
      {"Stefan", "Schulz", true, "Trombone", "", 1971},
    },
    {
      {"Tube"},
      {"Paul", "Hümpel", true, "Tube", "", 1945},
    },     
    {
      {"Timbals"},
      {"Rainer", "Seegers", true, "Timbal", "", 1952},          
      {"Wieland", "Welzel", true, "Timbal", "", 1972},
    },     
    {
      {"Drums"},
      {"Raphael", "Haeger", true, "Drums", "", 1971},          
      {"Fredi", "Müller", true, "Drums", "", 1942},
      {"Franz", "Schindlbeck", true, "Drums", "", 1967},
      {"Jan", "Schlichte", true, "Drums", "", 1972},
    },     
    {
      {"Harp"},
      {"Marie-Pierre", "Langlamet", false, "Harp", "", 1967},          
    },     
  };

  public SimpleTreeTable()
  {
    super("Simple JXTreeTable");    
    createAndAddComponents(getContentPane());

    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Create components and add them to the container.
   */
  private void createAndAddComponents(Container container)
  {
    JXTreeTable table = new JXTreeTable();
    addNodes(table);
    table.setRootVisible(true);
    table.getColumnModel().getColumn(0).setPreferredWidth(200);
    table.setColumnControlVisible(true);
    
    //workaround to avoid border fallback to default scrollpane-border after a LAF switch - JTable bug.
    JScrollPane scrollPane = new JScrollPane(table){
      @Override
      public void updateUI()
      {
        super.updateUI();
        setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
      }
    };
    container.add(scrollPane);
  }

  private void addNodes(JXTreeTable table)
  {
    DefaultMutableTreeTableNode orchestra = new DefaultMutableTreeTableNode("Berliner Philharmoniker Orchestra");
    DefaultTreeTableModel model = new OrchestraTreeTableModel(orchestra);

    for (int i = 0; i < data.length; i++)
    {
      DefaultMutableTreeTableNode part = new DefaultMutableTreeTableNode(data[i][0][0]);
      model.insertNodeInto(part, orchestra, orchestra.getChildCount());
      for (int j = 1; j < data[i].length; j++)
      {
        DefaultMutableTreeTableNode artist = new DefaultMutableTreeTableNode(new Artist((String) data[i][j][0], (String) data[i][j][1],
            (Boolean) data[i][j][2], (String) data[i][j][3], (String) data[i][j][4], (Integer) data[i][j][5]));
        model.insertNodeInto(artist, part, part.getChildCount());
      }
    }

    table.setTreeTableModel(model);
  }    
  
  /**
   * Static main method for application startup. 
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
          new SimpleTreeTable();
      }
    });
  }
  
  /**
   * 
   * TreeTableModel
   *
   */
  private static class OrchestraTreeTableModel extends DefaultTreeTableModel
  {
    public OrchestraTreeTableModel(TreeTableNode root)
    {
      super(root);
    }
    
    public int getColumnCount()
    {
      return 7;
    }

    public Class<?> getColumnClass(int column) 
    {
      switch (column)
      {
        case 0:
        case 1:
        case 2:
        case 4:
        case 5:
          return String.class;
        case 3:
          return Boolean.class;
        case 6:
          return String.class;
        default:
          return super.getColumnClass(column);
      }
    }

    public String getColumnName(int column)
    {
      switch (column)
      {
        case 0:
          return "Part / Artist";
        case 1:
          return "First name";
        case 2:
          return "Second Name";
        case 3:
          return "Male";
        case 4:
          return "Instrument";
        case 5:
          return "Role";
        case 6:
          return "Year of Birth";
        default:
          return super.getColumnName(column);
      }
    }

    @Override
    public Object getValueAt(Object node, int column)
    {
      if (((DefaultMutableTreeTableNode) node).getUserObject() instanceof Artist)
      {
        Artist artist = (Artist) ((DefaultMutableTreeTableNode) node).getUserObject();
        switch (column)
        {
          case 0:
            return artist.firstName + " " + artist.secondName;
          case 1:
            return artist.firstName;
          case 2:
            return artist.secondName;
          case 3:
            return artist.male;
          case 4:
            return artist.instrument;
          case 5:
            return artist.role;
          case 6:
            if (artist.yearOfBirth <= 0)
              return "?";
            return artist.yearOfBirth;
        }
      }

      if (column == 0)
        return super.getValueAt(node, column);
      return null;
    }
  }

  /**
   * 
   * Artist class
   *
   */
  private static class Artist
  {
    private String firstName;
    private String secondName;
    private Boolean male;
    private String instrument;
    private String role;
    private Integer yearOfBirth;
    
    public Artist(String firstName, String secondName, boolean male, String instrument, String role, Integer yearOfBirth)
    {
      this.firstName = firstName;
      this.secondName = secondName;
      this.male = male;
      this.instrument = instrument;
      this.role = role;
      this.yearOfBirth = yearOfBirth;
    }
  }
  
}