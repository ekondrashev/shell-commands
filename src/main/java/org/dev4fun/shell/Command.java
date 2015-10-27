package org.dev4fun.shell;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Command, Shell and related entities definitions.
 *
 * @author Eugene Kondrashev (eugene.kondrashev@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Command {
    String name();
    List<String> args();

    interface Shell {
        Execution exec(Command cmd);

        interface Execution {
            InputStream in();
            OutputStream out();
            InputStream err();
            Future<Execution.Result> result();

            interface Result<T> {
                boolean success();
                T adapt();
            }
        }

        class Cmd implements Shell {
            private final transient Path path;
            
            public Cmd(final Path path) {
                this.path = path;
            }

            @Override public Execution exec(final Command cmd) {
                throw new UnsupportedOperationException("#exec()");
            }
        }
    }
}
