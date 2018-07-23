// Generated from c:\Users\oscar\git\Featherweight-JavaScript\proj_master\FeatherweightJavaScript.g4 by ANTLR 4.7.1
 package edu.sjsu.fwjs.parser; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FeatherweightJavaScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, IF=8, ELSE=9, 
		WHILE=10, FUNCTION=11, VAR=12, PRINT=13, INT=14, BOOL=15, NULL=16, MUL=17, 
		DIV=18, PLUS=19, MIN=20, MOD=21, SEPARATOR=22, GT=23, LT=24, GE=25, LE=26, 
		EQ=27, IDENTIFIER=28, NEWLINE=29, LINE_COMMENT=30, BLOCK_COMMENT=31, WS=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "IF", "ELSE", 
		"WHILE", "FUNCTION", "VAR", "PRINT", "INT", "BOOL", "NULL", "MUL", "DIV", 
		"PLUS", "MIN", "MOD", "SEPARATOR", "GT", "LT", "GE", "LE", "EQ", "IDENTIFIER", 
		"NEWLINE", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'f'", "','", "'='", "'if'", "'else'", 
		"'while'", "'function'", "'var'", "'print'", null, null, "'null'", "'*'", 
		"'/'", "'+'", "'-'", "'%'", "';'", "'>'", "'<'", "'>='", "'<='", "'=='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "IF", "ELSE", "WHILE", 
		"FUNCTION", "VAR", "PRINT", "INT", "BOOL", "NULL", "MUL", "DIV", "PLUS", 
		"MIN", "MOD", "SEPARATOR", "GT", "LT", "GE", "LE", "EQ", "IDENTIFIER", 
		"NEWLINE", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FeatherweightJavaScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FeatherweightJavaScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00d3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\7\17u\n\17\f\17\16\17x\13\17\3\17\5\17{\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u0086\n\20\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\7\35\u00a8"+
		"\n\35\f\35\16\35\u00ab\13\35\3\36\5\36\u00ae\n\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\7\37\u00b8\n\37\f\37\16\37\u00bb\13\37\3\37\3\37"+
		"\3 \3 \3 \3 \7 \u00c3\n \f \16 \u00c6\13 \3 \3 \3 \3 \3 \3!\6!\u00ce\n"+
		"!\r!\16!\u00cf\3!\3!\3\u00c4\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"\3\2\b\3\2\63;\3\2\62;\5\2C\\a"+
		"ac|\6\2\62;C\\aac|\4\2\f\f\17\17\4\2\13\13\"\"\2\u00da\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13"+
		"K\3\2\2\2\rM\3\2\2\2\17O\3\2\2\2\21Q\3\2\2\2\23T\3\2\2\2\25Y\3\2\2\2\27"+
		"_\3\2\2\2\31h\3\2\2\2\33l\3\2\2\2\35z\3\2\2\2\37\u0085\3\2\2\2!\u0087"+
		"\3\2\2\2#\u008c\3\2\2\2%\u008e\3\2\2\2\'\u0090\3\2\2\2)\u0092\3\2\2\2"+
		"+\u0094\3\2\2\2-\u0096\3\2\2\2/\u0098\3\2\2\2\61\u009a\3\2\2\2\63\u009c"+
		"\3\2\2\2\65\u009f\3\2\2\2\67\u00a2\3\2\2\29\u00a5\3\2\2\2;\u00ad\3\2\2"+
		"\2=\u00b3\3\2\2\2?\u00be\3\2\2\2A\u00cd\3\2\2\2CD\7*\2\2D\4\3\2\2\2EF"+
		"\7+\2\2F\6\3\2\2\2GH\7}\2\2H\b\3\2\2\2IJ\7\177\2\2J\n\3\2\2\2KL\7h\2\2"+
		"L\f\3\2\2\2MN\7.\2\2N\16\3\2\2\2OP\7?\2\2P\20\3\2\2\2QR\7k\2\2RS\7h\2"+
		"\2S\22\3\2\2\2TU\7g\2\2UV\7n\2\2VW\7u\2\2WX\7g\2\2X\24\3\2\2\2YZ\7y\2"+
		"\2Z[\7j\2\2[\\\7k\2\2\\]\7n\2\2]^\7g\2\2^\26\3\2\2\2_`\7h\2\2`a\7w\2\2"+
		"ab\7p\2\2bc\7e\2\2cd\7v\2\2de\7k\2\2ef\7q\2\2fg\7p\2\2g\30\3\2\2\2hi\7"+
		"x\2\2ij\7c\2\2jk\7t\2\2k\32\3\2\2\2lm\7r\2\2mn\7t\2\2no\7k\2\2op\7p\2"+
		"\2pq\7v\2\2q\34\3\2\2\2rv\t\2\2\2su\t\3\2\2ts\3\2\2\2ux\3\2\2\2vt\3\2"+
		"\2\2vw\3\2\2\2w{\3\2\2\2xv\3\2\2\2y{\7\62\2\2zr\3\2\2\2zy\3\2\2\2{\36"+
		"\3\2\2\2|}\7v\2\2}~\7t\2\2~\177\7w\2\2\177\u0086\7g\2\2\u0080\u0081\7"+
		"h\2\2\u0081\u0082\7c\2\2\u0082\u0083\7n\2\2\u0083\u0084\7u\2\2\u0084\u0086"+
		"\7g\2\2\u0085|\3\2\2\2\u0085\u0080\3\2\2\2\u0086 \3\2\2\2\u0087\u0088"+
		"\7p\2\2\u0088\u0089\7w\2\2\u0089\u008a\7n\2\2\u008a\u008b\7n\2\2\u008b"+
		"\"\3\2\2\2\u008c\u008d\7,\2\2\u008d$\3\2\2\2\u008e\u008f\7\61\2\2\u008f"+
		"&\3\2\2\2\u0090\u0091\7-\2\2\u0091(\3\2\2\2\u0092\u0093\7/\2\2\u0093*"+
		"\3\2\2\2\u0094\u0095\7\'\2\2\u0095,\3\2\2\2\u0096\u0097\7=\2\2\u0097."+
		"\3\2\2\2\u0098\u0099\7@\2\2\u0099\60\3\2\2\2\u009a\u009b\7>\2\2\u009b"+
		"\62\3\2\2\2\u009c\u009d\7@\2\2\u009d\u009e\7?\2\2\u009e\64\3\2\2\2\u009f"+
		"\u00a0\7>\2\2\u00a0\u00a1\7?\2\2\u00a1\66\3\2\2\2\u00a2\u00a3\7?\2\2\u00a3"+
		"\u00a4\7?\2\2\u00a48\3\2\2\2\u00a5\u00a9\t\4\2\2\u00a6\u00a8\t\5\2\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa:\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ae\7\17\2\2\u00ad\u00ac"+
		"\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\f\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b2\b\36\2\2\u00b2<\3\2\2\2\u00b3\u00b4\7\61\2"+
		"\2\u00b4\u00b5\7\61\2\2\u00b5\u00b9\3\2\2\2\u00b6\u00b8\n\6\2\2\u00b7"+
		"\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\b\37\2\2\u00bd"+
		">\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c0\7,\2\2\u00c0\u00c4\3\2\2\2"+
		"\u00c1\u00c3\13\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7"+
		"\u00c8\7,\2\2\u00c8\u00c9\7\61\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\b "+
		"\2\2\u00cb@\3\2\2\2\u00cc\u00ce\t\7\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\b!\2\2\u00d2B\3\2\2\2\13\2vz\u0085\u00a9\u00ad\u00b9\u00c4\u00cf"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}